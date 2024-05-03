package storageInterface;

import storage.City;
import storage.StorageInfo;

import java.io.IOException;
import java.util.ArrayList;

public interface StorageInterface {
    public ArrayList<City> getCitiesList();

    public void add(City city);

    public void update(City city);

    public void clear();

    public void save() throws IOException;

    public void load() throws IOException;

    public int countGreaterThanCapital(Boolean capital);

    public void removeAllByCarCode(Long carCode);

    public StorageInfo getInfo();

    public void removeById(int id);

    public void removeFirst();

    public void removeGreater(City city);

    public void removeLower(City city);

    public Long sumOfCarCode();
}
