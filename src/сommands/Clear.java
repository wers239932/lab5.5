package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.util.ArrayList;

public class Clear implements Command {


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
