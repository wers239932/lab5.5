package storage;

import storageInterface.StorageInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ProxyStorage implements StorageInterface {
    private Storage storage;
    private DBManager dbManager;

    @Override
    public ArrayList<City> getCitiesList() {
        return storage.getCitiesList();
    }

    @Override
    public void add(City city) {
        storage.add(city);

    }

    @Override
    public void update(City city) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public void load() throws IOException {

    }

    @Override
    public StorageInfo getInfo() {
        return null;
    }

    @Override
    public void removeFirst() {

    }

    @Override
    public Stream<City> getCitiesStream() {
        return null;
    }

    @Override
    public void getToCollect(Stream<City> cityStream) {

    }
}
