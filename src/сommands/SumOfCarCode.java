package сommands;

import api.Request;
import storage.Storage;
import storageInterface.StorageInterface;
import cli.Command;
import cli.IOInterface;
import cli.commandExceptions.CommandException;

import java.util.ArrayList;

public class SumOfCarCode implements Command {
    private StorageInterface storage;

    public SumOfCarCode(StorageInterface storage) {
        this.storage = storage;
    }

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        response.add("сумма carcode по всем объектам равна " + storage.sumOfCarCode().toString());
        return response;
    }

    @Override
    public String getName() {
        return "sum_of_car_code";
    }

    @Override
    public String getDescription() {
        return "sum_of_car_code : вывести сумму значений поля carCode для всех элементов коллекции";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
