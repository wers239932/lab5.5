package сommands;

import storageInterface.StorageInterface;
import cli.Command;
import cli.IOInterface;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.objectExceptions.CarCodeException;

import java.util.ArrayList;

public class RemoveAllByCarCode implements Command {
    private StorageInterface storage;
    private Long carCode;

    public RemoveAllByCarCode(StorageInterface storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<String> execute(ArrayList<String> args, IOInterface terminal) throws CommandException {
        try {
            this.carCode = City.parseCarCode(args.get(0));
        } catch (CarCodeException e) {
            throw new CommandException(e.getMessage());
        }

        ArrayList<String> response = new ArrayList<>();
        storage.removeAllByCarCode(carCode);
        response.add("объекты удалены");
        return response;
    }

    @Override
    public String getName() {
        return "remove_all_by_car_code";
    }

    @Override
    public String getDescription() {
        return "remove_all_by_car_code carCode : удалить из коллекции все элементы, значение поля carCode которого эквивалентно заданному";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
