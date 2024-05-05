import app.App;
import app.AppClient;
import app.AppServer;

public class Main {
    public static void main(String[] args) {
        switch (System.getenv("MODE")) {
            case ("standalone"): {
                App.run();
                break;
            }
            case ("server"): {
                AppServer.Run();
                break;
            }
            case ("client"): {
                AppClient.Run();
                break;
            }
        }
    }
}