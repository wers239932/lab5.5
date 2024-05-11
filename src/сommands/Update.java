package сommands;

import api.Request;
import cli.Command;
import cli.commandExceptions.CommandException;
import storage.City;
import storage.Storage;
import storage.objectExceptions.IdException;

import java.util.ArrayList;

public class Update implements Command {

    @Override
    public ArrayList<String> execute(Request request, Storage storage) throws CommandException {
        int id;
        try {
            id = City.parseId((String) request.getArgs().get(0));
        } catch (IdException e) {
            throw new CommandException(e.getMessage());
        }
        City city = (City) request.getData();
        for (City cityStored : storage.getCitiesList()) {
            if (cityStored.getId() == city.getId()) {
                cityStored.setName(city.getName());
                cityStored.setArea(city.getArea());
                cityStored.setGovernment(city.getGovernment());
                cityStored.setGovernor(city.getGovernor());
                cityStored.setCapital(city.getCapital());
                cityStored.setCarCode(city.getCarCode());
                cityStored.setMetersAboveSeaLevel(city.getMetersAboveSeaLevel());
                cityStored.setPopulation(city.getPopulation());
                cityStored.setCoordinates(city.getCoordinates());
            }
        }
        ArrayList<String> response = new ArrayList<>();
        storage.update(city);
        response.add("объект обновлен");
        return response;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
