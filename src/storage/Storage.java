package storage;

import dal.DataAccessLayer;
import storageInterface.StorageInterface;

import java.io.IOException;
import java.io.Serializable;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public StorageInfo getInfo() {
        return new StorageInfo(this.objects.size(), this.creationDate);
    }


    @Override
    public void removeFirst() {
        if (!this.objects.isEmpty())
            this.objects.remove(0);
    }

    @Override
    public Stream<City> getCitiesStream() {
        return this.objects.stream();
    }

    @Override
    public void getToCollect(Stream<City> cityStream) {
        this.objects = cityStream.collect(Collectors.toCollection(ArrayList::new));
    }
}
