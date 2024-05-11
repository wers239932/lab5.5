package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.util.ArrayList;

public class Help implements Command {
    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        return null;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
