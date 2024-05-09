package app;

import cli.CommandSender;
import cli.Terminal;
import client.Client;

import java.net.UnknownHostException;

public class AppClient {
    public static void run() {
        String host = System.getenv("SERVER_HOST");
        int port = Integer.parseInt(System.getenv("SERVER_PORT"));
        Client client = null;
        try {
            client = new Client(host, port);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        CommandSender CommandSender = new CommandSender(new Terminal(), client);
        CommandSender.start();
    }
}
