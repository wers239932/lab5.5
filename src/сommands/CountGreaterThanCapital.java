package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;

import java.util.ArrayList;

public class CountGreaterThanCapital implements Command {


    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        if (request.getArgs().isEmpty()) throw new CommandException("неверный набор данных");
        Boolean capital;
        try {
            capital = City.parseCapital((String) request.getArgs().get(0));
        }catch (IndexOutOfBoundsException e) {
            throw new CommandException("не введен аргумент");
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
        ArrayList<String> response = new ArrayList<>();
        int amount = (int) storage.getCitiesStream().filter((city) -> city.getCapital().compareTo(capital) > 0).count();
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
