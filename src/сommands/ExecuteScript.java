package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;

import java.util.ArrayList;

public class ExecuteScript implements Command {


    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        storage.add((City) request.getData());
        ArrayList<String> response = new ArrayList<>();
        response.add("выполняю скрипт");
        return response;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public Boolean getNeedObject() {
        return false;
    }

}
