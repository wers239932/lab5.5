package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;

import java.util.ArrayList;

public class Add implements Command {

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        storage.add((City) request.getData());
        ArrayList<String> response = new ArrayList<>();
        response.add("добавлен город");
        return response;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public Boolean getNeedObject() {
        return true;
    }

}
