package сommands;

import api.Request;
import storage.Storage;
import storageInterface.StorageInterface;
import cli.Command;
import cli.IOInterface;
import cli.commandExceptions.CommandException;
import storage.City;

import java.util.ArrayList;

public class Show implements Command {
    private StorageInterface storage;

    public Show() {

    }

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        for (City city : storage.getCitiesList()) {
            response.add((city).toString());
        }
        return response;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
