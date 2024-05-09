package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class Save implements Command {

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        try {
            storage.save();
        } catch (IOException e) {
            throw new CommandException("ошибка ввода-вывода");
        }
        ArrayList<String> response = new ArrayList<>();
        response.add("коллекция сохранена");
        return response;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
