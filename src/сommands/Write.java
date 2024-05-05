package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.util.ArrayList;

public class Write implements Command {

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        if (request.getArgs().isEmpty()) throw new CommandException("неверный набор данных");
        ArrayList<String> response = new ArrayList<>();
        response.add((String) request.getArgs().get(0));
        return response;
    }

    @Override
    public String getName() {
        return "write";
    }

    @Override
    public String getDescription() {
        return "write : пишет первое слово ввода";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
