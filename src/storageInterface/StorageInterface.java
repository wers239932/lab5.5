package storageInterface;

import storage.City;
import storage.StorageInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public interface StorageInterface {
    public ArrayList<City> getCitiesList();

    public void add(City city);

    public void update(City city);

    public void clear();

    public void save() throws IOException;

    public void load() throws IOException;

    public StorageInfo getInfo();

    public void removeFirst();

    public Stream<City> getCitiesStream();

    public void getToCollect(Stream<City> cityStream);
}
