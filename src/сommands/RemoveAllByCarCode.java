package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;
import storage.objectExceptions.CarCodeException;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class RemoveAllByCarCode implements Command {
    private StorageInterface storage;
    private Long carCode;

    public RemoveAllByCarCode() {

    }

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        try {
            this.carCode = City.parseCarCode((String) request.getArgs().get(0));
        } catch (CarCodeException e) {
            throw new CommandException(e.getMessage());
        } catch (NullPointerException e) {
            throw new CommandException("не введен аргумент");
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
