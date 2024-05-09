package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;
import storage.objectExceptions.IdException;

import java.util.ArrayList;

public class RemoveById implements Command {


    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        int id;
        try {
            id = City.parseId((String) request.getArgs().get(0));
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
