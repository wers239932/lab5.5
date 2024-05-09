package app;

import cli.Command;
import dal.DataAccessLayer;
import server.Server;
import storage.Storage;
import сommands.CommandArrayFiller;

import java.io.IOException;
import java.util.HashMap;

public class AppServer {
    public static void run() {
        DataAccessLayer dataAccessLayer = new DataAccessLayer(System.getenv("SAVEFILE"));
        Storage storage = null;
        try {
            storage = new Storage(dataAccessLayer);
        } catch (IOException e) {
            System.out.println("не удалось загрузить коллекцию");
            System.exit(1);
        }
        String host = System.getenv("SERVER_HOST");
        int port = Integer.parseInt(System.getenv("SERVER_PORT"));
        CommandArrayFiller.setBasicCommands();
        HashMap<String, Command> commandMap = CommandArrayFiller.getCommandMap();
        Server server = new Server(host, port, storage, commandMap);
        server.handle();

        //раскомментировать если сделаем терминал на сервере
        /*ArrayList<Command> commandArray = new ArrayList<>();
        commandArray.add(new Save(storage));
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray);
        commandExecuter.start();*/

    }
}
