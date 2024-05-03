package app;

import cli.CommandSender;
import cli.Terminal;
import client.Client;
import сommands.CommandArrayFiller;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class AppClient {
    public static void Run() {
        String host = System.getenv("SERVER_HOST");
        int port = Integer.parseInt(System.getenv("SERVER_PORT"));
        Client client = null;
        try {
            client = new Client(host, port);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        //ArrayList commandArray = CommandArrayFiller.setBasicCommands(client);
        CommandSender commandExecuter = new CommandSender(new Terminal());
        commandExecuter.start();
    }
}
