package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.util.ArrayList;

public class Exit implements Command {
    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        response.add("выход");
        return response;
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
