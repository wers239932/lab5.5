package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.Storage;
import storage.StorageInfo;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class Info implements Command {
    private StorageInterface storage;

    public Info(StorageInterface storage) {
        this.storage = storage;
    }


    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        StorageInfo storageInfo = storage.getInfo();
        response.add("Дата созданиия: " + storageInfo.getCreationDate().toString());
        response.add("количество элементов в памяти: " + storageInfo.getSize());
        return response;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
