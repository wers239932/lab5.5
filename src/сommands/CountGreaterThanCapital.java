package сommands;

import storageInterface.StorageInterface;
import cli.Command;
import cli.IOInterface;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.objectExceptions.CapitalException;

import java.util.ArrayList;

public class CountGreaterThanCapital implements Command {
    private StorageInterface storage;

    public CountGreaterThanCapital(StorageInterface storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<String> execute(ArrayList<String> args, IOInterface terminal) throws CommandException {
        Boolean capital;
        try {
            capital = City.parseCapital(args.get(0));
        } catch (CapitalException e) {
            throw new CommandException(e.getMessage());
        }
        int amount = storage.countGreaterThanCapital(capital);
        ArrayList<String> response = new ArrayList<>();
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
