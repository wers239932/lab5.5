package client;

import storageInterface.StorageInterface;
import api.ProtocolInfo;
import api.Request;
import api.RequestNames;
import api.Response;
import storage.City;
import storage.StorageInfo;

import java.io.*;
import java.net.*;
import java.time.Duration;
import java.util.ArrayList;

public class Client {
    public final static Duration timeout = Duration.ofSeconds(1);
    private InetAddress address;
    private int port;

    public Client(String host, int port) throws UnknownHostException {
        this.address = InetAddress.getByName(host);
        this.port = port;
    }

    public Response sendRequest(Request request) {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout((int) timeout.toMillis());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(request);
            stream.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (outputStream.toByteArray().length > ProtocolInfo.messageSize)
            throw new RuntimeException("реквест не влезает в размер сообщения");

        DatagramPacket dp = new DatagramPacket(outputStream.toByteArray(), outputStream.size(), address, port);
        try {
            socket.send(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] buffer = new byte[ProtocolInfo.messageSize];
        DatagramPacket udpResp = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(udpResp);
        } catch (SocketTimeoutException e) {
            System.out.println("превышено время ожидания от сервера");
            return sendRequest(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        byte[] byteResponse = new byte[0];
        byte[] idBytes = new byte[4];
        int id;
        byte total;
        byte index;
        while (true) {
            try {
                inputStream.read(idBytes);
                total = (byte) inputStream.read();
                index = (byte) inputStream.read();
                byte[] part = inputStream.readAllBytes();
                byte[] c = new byte[byteResponse.length + part.length];
                System.arraycopy(byteResponse, 0, c, 0, byteResponse.length);
                System.arraycopy(part, 0, c, byteResponse.length, part.length);
                byteResponse = c;
                if (index + 1 == total) break;
                socket.receive(udpResp);
                inputStream = new ByteArrayInputStream(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteResponse);
            ObjectInputStream inputStream1 = new ObjectInputStream(byteArrayInputStream);
            Response response = (Response) inputStream1.readObject();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
