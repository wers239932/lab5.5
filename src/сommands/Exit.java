package сommands;

import cli.Command;
import cli.IOInterface;
import cli.commandExceptions.CommandException;

import java.util.ArrayList;

public class Exit implements Command {
    @Override
    public ArrayList<String> execute(ArrayList<String> args, IOInterface terminal) throws CommandException {
        System.exit(0);
        return null;
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
