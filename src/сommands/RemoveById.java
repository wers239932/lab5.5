package сommands;

import storageInterface.StorageInterface;
import cli.Command;
import cli.IOInterface;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.objectExceptions.IdException;

import java.util.ArrayList;

public class RemoveById implements Command {
    private StorageInterface storage;
    private int id;

    public RemoveById(StorageInterface storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<String> execute(ArrayList<String> args, IOInterface terminal) throws CommandException {
        try {
            this.id = City.parseId(args.get(0));
        } catch (IdException e) {
            throw new CommandException(e.getMessage());
        }
        ArrayList<String> response = new ArrayList<>();
        storage.removeById(id);
        response.add("объект удален");
        return response;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
