package server;

import api.ProtocolInfo;
import api.Request;
import api.RequestStatus;
import api.Response;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.Duration;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Server {
    private Storage storage;
    public final static Duration timeout = Duration.ofMillis(50);
    private Scanner scanner;
    private Logger logger = Logger.getLogger("MyLog");
    private HashMap<String, Command> commandMap;
    private DatagramChannel datagramChannel;

    public Server(String host, int port, Storage storage, HashMap<String, Command> commandMap) {
        this.commandMap = commandMap;
        this.scanner = new Scanner(System.in);
        this.storage = storage;
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);
            this.datagramChannel = DatagramChannel.open();
            this.datagramChannel.bind(socketAddress);
            this.datagramChannel.configureBlocking(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Logger logger = Logger.getLogger("MyLog");
        try {
            FileHandler fh = new FileHandler("server_logger.log", true);
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            logger.info("сервер создан");
        } catch (SecurityException | IOException e) {
            logger.log(Level.SEVERE, "Произошла ошибка при работе с FileHandler.", e);
        }
    }

    public void handle() {
        while (true) {
            int available;
            try {
                available = System.in.available();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (available > 0) {
                String command = scanner.nextLine();
                switch (command) {
                    case ("exit"): {
                        try {
                            storage.save();
                            System.out.println("коллекция сохранена");
                            logger.fine("коллекция сохранена");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("завершение работы");
                        logger.info("выключение сервера");
                        System.exit(0);
                    }
                    case ("save"): {
                        try {
                            storage.save();
                            System.out.println("коллекция сохранена");
                            logger.fine("коллекция сохранена");
                        } catch (IOException e) {
                            logger.severe("ошибка сохранения");
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {
                ByteBuffer data = ByteBuffer.allocate(ProtocolInfo.messageSize);
                try {
                    SocketAddress clientAddress = this.datagramChannel.receive(data);
                    if (clientAddress != null) {
                        Request request = this.readRequest(data);
                        if (request != null) {
                            String commandName = request.getCommandName();
                            Response response = null;
                            switch (commandName) {
                                case ("getCommands"): {
                                    ArrayList<Command> commands = new ArrayList(this.commandMap.values());
                                    response = new Response<>(commands, RequestStatus.DONE, null);
                                    break;
                                }
                                case ("help"): {
                                    ArrayList<String> output = new ArrayList<>();
                                    for (Command command : this.commandMap.values()) {
                                        output.add(command.getDescription());
                                        response = new Response<>(output, RequestStatus.DONE, null);
                                    }
                                    break;
                                }
                                default: {
                                    Command command = this.commandMap.get(commandName);
                                    ArrayList<String> output = null;
                                    try {
                                        output = command.execute(request, this.storage);
                                        response = new Response<>(output, RequestStatus.DONE, null);
                                    } catch (CommandException e) {
                                        response = new Response<>(RequestStatus.FAILED, e.getMessage());
                                        logger.severe("запрос клиента не выполнен, ошибка: " + e.getMessage());
                                    }
                                    break;
                                }
                            }
                            this.sendReply(response, this.datagramChannel, clientAddress);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public void sendReply(Response object, DatagramChannel datagramChannel, SocketAddress address) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] sendData = byteArrayOutputStream.toByteArray();
        int len = sendData.length;
        Random random = new Random();
        int id = random.nextInt();
        int dataSize = ProtocolInfo.messageSize - ProtocolInfo.headerSize;
        byte total = (byte) ((len + dataSize - 1) / dataSize);
        byte index = 0;
        while (index < total) {
            ByteArrayOutputStream part = new ByteArrayOutputStream();
            byte[] bytes = ByteBuffer.allocate(4).putInt(id).array();
            part.write(bytes);
            part.write(total);
            part.write(index);
            int end = (index + 1) * dataSize;
            if (end > len) end = len;
            part.write(Arrays.copyOfRange(sendData, index * dataSize, end));
            index++;
            datagramChannel.send(ByteBuffer.wrap(part.toByteArray()), address);
        }
        logger.info("отправлен ответ");
    }

    private Request readRequest(ByteBuffer data) {
        ByteArrayInputStream dataStream = new ByteArrayInputStream(data.array());
        Request request;
        try {
            ObjectInputStream objectStream = new ObjectInputStream(dataStream);
            request = (Request) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("сообщение клиента не может быть прочитано");
            throw new RuntimeException(e);
        }
        logger.info("прочитан запрос");
        return request;
    }
}
