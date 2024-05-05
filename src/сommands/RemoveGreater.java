package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class RemoveGreater implements Command {
    private StorageInterface storage;

    public RemoveGreater() {

    }

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        City city = (City) request.getData();
        ArrayList<String> response = new ArrayList<>();
        storage.removeGreater(city);
        response.add("элементы удалены");
        return response;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }

    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
