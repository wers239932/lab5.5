package storage;

import storageInterface.StorageInterface;
import dal.DataAccessLayer;

import java.io.IOException;
import java.io.Serializable;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class Storage implements StorageInterface, Serializable {
    private ArrayList<City> objects;
    private Date creationDate;
    private DataAccessLayer dataAccessLayer;

    public Storage(DataAccessLayer dataAccessLayer) throws IOException {
        this.objects = new ArrayList<>();
        this.dataAccessLayer = dataAccessLayer;
        this.load();

        this.creationDate = new Date();
    }

    @Override
    public void load() throws IOException {
        ArrayList<String> records = this.dataAccessLayer.readAllRecords();
        City city;
        for (String record : records) {
            try {
                city = City.parseCity(record.split(","));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.objects.add(city);
        }
    }

    @Override
    public void save() throws IOException {
        ArrayList<String> contents = new ArrayList<>();
        contents.add("id,name,coordinate_x,coordinate_y,creationDate,area,population,metersAboveSeaLevel,capital,carCode,government,governor_birthday");
        for (City city : this.objects) {
            contents.add(city.toString());
        }
        dataAccessLayer.writeAllRecords(contents);
    }

    @Override
    public ArrayList<City> getCitiesList() {
        CityNameComparator comparator = new CityNameComparator();
        this.objects.sort(comparator);
        return this.objects;
    }


    @Override
    public void add(City city) {
        Random randonGenerator = new Random();
        city.setId(randonGenerator.nextInt(Integer.MAX_VALUE) + 1);
        Clock clock = Clock.system(ZoneId.of("Europe/Moscow"));
        city.setCreationDate(ZonedDateTime.now(clock));
        this.objects.add(city);
    }

    @Override
    public void update(City city) {
        for (City cityStored : this.objects) {
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
    }


    @Override
    public void clear() {
        this.objects.clear();
    }

    @Override
    public int countGreaterThanCapital(Boolean capital) {
        return (int) this.objects.stream()
                .filter((city) -> city.getCapital().compareTo(capital) > 0)
                .count();
    }

    @Override
    public StorageInfo getInfo() {
        return new StorageInfo(this.objects.size(), this.creationDate);
    }

    public void removeAllByCarCode(Long carCode) {
        this.objects.removeIf(cityStored -> cityStored.getCarCode() == carCode);
    }

    @Override
    public void removeById(int id) {
        this.objects.removeIf(cityStored -> cityStored.getId() == id);
    }

    @Override
    public void removeFirst() {
        if (!this.objects.isEmpty())
            this.objects.remove(0);
    }

    @Override
    public void removeGreater(City city) {
        this.objects = this.objects.stream()
                .filter(obj -> obj.compareTo(city) > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void removeLower(City city) {
        this.objects = this.objects.stream().filter(cityStored -> cityStored.compareTo(city) < 0).collect(Collectors.toCollection(ArrayList::new));;
    }

    @Override
    public Long sumOfCarCode() {
        return this.objects.stream().mapToLong(City::getCarCode).sum();
    }
}
