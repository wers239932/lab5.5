package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class CountGreaterThanCapital implements Command {
    private StorageInterface storage;

    public CountGreaterThanCapital(StorageInterface storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException{
        Boolean capital;
        try{
            capital = City.parseCapital((String) request.getArgs().get(0));
        }
        catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
        ArrayList<String> response = new ArrayList<>();
        int amount = storage.countGreaterThanCapital(capital);
        response.add("количество объектов с полем carCode больше заданного равно " + amount);
        return response;
    }


    @Override
    public String getName() {
        return "count_greater_than_capital";
    }

    @Override
    public String getDescription() {
        return "count_greater_than_capital capital : вывести количество элементов, значение поля capital которых больше заданного";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
