package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class Clear implements Command {
    private StorageInterface storage;

    public Clear(StorageInterface storage) {
        this.storage = storage;
    }


    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        storage.clear();
        response.add("коллекция очищена");
        return response;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
