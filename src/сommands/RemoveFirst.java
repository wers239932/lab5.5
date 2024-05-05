package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class RemoveFirst implements Command {
    private StorageInterface storage;

    public RemoveFirst(StorageInterface storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        storage.removeFirst();
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return "remove_first : удалить первый элемент из коллекции";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
