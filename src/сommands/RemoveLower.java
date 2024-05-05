package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class RemoveLower implements Command {
    private StorageInterface storage;

    public RemoveLower(StorageInterface storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        City city = (City) request.getData();
        ArrayList<String> response = new ArrayList<>();
        storage.removeLower(city);
        response.add("элементы удалены");
        return response;
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
