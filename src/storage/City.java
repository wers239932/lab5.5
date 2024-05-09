package storage;

import storage.objectExceptions.*;

import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Random;

/**
 * Класс город, объектами которого мы манипулируем
 */

public class City implements Comparable<City>, Serializable {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    public static String parseName(String nameToCheck) throws NameCityException {
        if (nameToCheck.isEmpty()) throw new NameCityException("название не может быть null");
        return nameToCheck;
    }

    public static int parseId(String idToCheck) throws IdException {
        int id;
        if (!idToCheck.matches("[1-9]\\d*\\s*")) {
            throw new IdException("id должен быть целым неотрицательным числом");
        }
        try {
            id = Integer.parseInt(idToCheck);
        } catch (Exception e) {
            throw new IdException("не удалось преобразовать из строки в int");
        }
        return id;
    }

    /**
     * название
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * @see Coordinates
     */
    private Coordinates coordinates; //Поле не может быть null

    /**
     * дата создания объекта
     */
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    public static ZonedDateTime parseZonedDateTime(String date) {
        ZonedDateTime zonedDateTime;
        try {
            zonedDateTime = ZonedDateTime.parse(date);
        } catch (Exception e) {
            throw new DateTimeException("не удалось преобразовать строку в ZonedDateTime");
        }
        return zonedDateTime;
    }

    /**
     * площадь города
     */
    private Long area; //Значение поля должно быть больше 0, Поле не может быть null

    public static long parseArea(String area) throws AreaException {
        long y;
        try {
            y = Long.parseLong(area);
        } catch (Exception e) {
            throw new AreaException("не удалось преобразовать из строки в long");
        }
        if (y < 0) throw new AreaException("area<0");
        return y;
    }

    /**
     * население
     */
    private int population; //Значение поля должно быть больше 0

    public static int parsePopulation(String population) throws Exception {
        int y;
        try {
            y = Integer.parseInt(population);
        } catch (Exception e) {
            throw new PopulationException("не удалось преобразовать из строки в long");
        }
        if (y < 0) throw new PopulationException("население меньше 0");
        return y;
    }

    /**
     * высота над уровнем моря, должно быть больше 0
     */
    private double metersAboveSeaLevel;

    public static double parseMetersAboveSeaLevel(String x) throws HeightException {
        double y;
        try {
            y = Double.parseDouble(x);
        } catch (Exception e) {
            throw new HeightException("не удалось преобразовать из строки в Double");
        }
        return y;
    }

    /**
     * наличие столицы
     */
    private Boolean capital; //Поле может быть null

    public static Boolean parseCapital(String capital) throws CapitalException {
        Boolean y;
        if (!capital.isEmpty()) {
            try {
                y = Boolean.parseBoolean(capital);
                return y;
            } catch (Exception e) {
                throw new CapitalException("не удалось преобразовать из строки в Boolean");
            }
        } else return null;
    }

    /**
     * carCode, некоторое поле, больше 0, меньше 1000
     */
    private Long carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000, Поле может быть null

    public static Long parseCarCode(String carcode) throws CarCodeException {
        Long carcode1;
        if (!carcode.isEmpty() && !carcode.equals("null")) {
            try {
                carcode1 = Long.parseLong(carcode);
            } catch (Exception e) {
                throw new CarCodeException("не удалось преобразовать из строки в Long");
            }
            if (carcode1 <= 0 || carcode1 > 1000) throw new CarCodeException("carcode не в промежутке от 0 до 999");
            return carcode1;
        } else return null;
    }

    /**
     * @see Government
     */
    private Government government; //Поле может быть null

    public static Government parseGovernment(String government) throws GovernmentException {
        Government government1;
        if (!government.isEmpty() && !government.equals("null")) {
            try {
                government1 = Government.valueOf(government);
            } catch (Exception e) {
                throw new GovernmentException("не удалось преобразовать из строки в enum");
            }
            return government1;
        } else return null;
    }

    /**
     * @see Human
     */
    private Human governor; //Поле может быть null

    private static final Random randonGenerator = new Random();

    public City(String name, Coordinates coordinates, Long area, int population, double metersAboveSeaLevel, Boolean capital, Long carCode, Government government, Human governor) {
        this.id = randonGenerator.nextInt(Integer.MAX_VALUE) + 1;
        this.name = name;
        Clock clock = Clock.system(ZoneId.of("Europe/Moscow"));
        this.creationDate = ZonedDateTime.now(clock);
        this.area = area;
        this.government = government;
        this.governor = governor;
        this.capital = capital;
        this.carCode = carCode;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.population = population;
        this.coordinates = coordinates;
    }

    // **использовать только для метода update**
    public City(int id, String name, Coordinates coordinates, Long area, int population, double metersAboveSeaLevel, Boolean capital, Long carCode, Government government, Human governor) {
        this.id = id;
        this.name = name;
        Clock clock = Clock.system(ZoneId.of("Europe/Moscow"));
        this.creationDate = ZonedDateTime.now(clock);
        this.area = area;
        this.government = government;
        this.governor = governor;
        this.capital = capital;
        this.carCode = carCode;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.population = population;
        this.coordinates = coordinates;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Boolean getCapital() {
        return capital;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getArea() {
        return area;
    }

    public Government getGovernment() {
        return government;
    }

    public Human getGovernor() {
        return governor;
    }

    public int getId() {
        return id;
    }

    public Long getCarCode() {
        return carCode;
    }

    public Double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public int getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public void setCarCode(Long carCode) {
        this.carCode = carCode;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    void setId(int id) {
        this.id = id;
    }

    public void setMetersAboveSeaLevel(double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        if (Objects.isNull(this.government))
            return this.id + "," + this.name + "," + this.coordinates.toString() + "," +
                    this.creationDate + "," + this.area
                    + "," + this.population + "," + this.metersAboveSeaLevel
                    + "," + this.capital + ","
                    + this.carCode + "," + "null" + ","
                    + this.governor.toString();
        else
            return this.id + "," + this.name + "," + this.coordinates.toString() + "," +
                    this.creationDate + "," + this.area
                    + "," + this.population + "," + this.metersAboveSeaLevel
                    + "," + this.capital + ","
                    + this.carCode + "," + this.government.toString() + ","
                    + this.governor.toString();
    }

    @Override
    public int compareTo(City o) {
        int diff = this.area.compareTo(o.getArea());
        if (diff == 0) {
            diff = id - o.getId();
        }
        return diff;
    }

    /**
     * парсит набор строк в город
     *
     * @param args
     * @throws CoordinatesException
     * @throws NameCityException
     * @throws AreaException
     * @throws PopulationException
     * @throws HeightException
     * @throws CapitalException
     * @throws CarCodeException
     * @throws GovernmentException
     * @throws GovernorException
     */
    public static City parseCity(String[] args) throws CoordinatesException, NameCityException, AreaException, PopulationException, HeightException, CapitalException, CarCodeException, GovernmentException, GovernorException {
        if (args.length != 12) {
            throw new IncorrectDataExceptoin("некорректное количество данных, введено " + args.length + " аргументов");
        } else {
            Integer id = null;
            String name = null;
            Coordinates coordinates = null;
            ZonedDateTime creationDate = null;
            Long area = null;
            Integer population = null;
            Double metersAboveSeaLevel = null;
            Boolean capital = null;
            Long carCode = null;
            Government government = null;
            Human governor = null;
            try {
                id = City.parseId(args[0].trim());
                name = City.parseName(args[1].trim());
                float x = Coordinates.parseXCoord(args[2].trim());
                long y = Coordinates.parseYCoord(args[3].trim());
                coordinates = new Coordinates(x, y);
                creationDate = City.parseZonedDateTime(args[4].trim());
                area = City.parseArea(args[5].trim());
                population = City.parsePopulation(args[6].trim());
                metersAboveSeaLevel = City.parseMetersAboveSeaLevel(args[7].trim());
                capital = City.parseCapital(args[8].trim());
                carCode = City.parseCarCode(args[9].trim());
                government = City.parseGovernment(args[10].trim());
                governor = Human.parseGovernor(args[11].trim());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            City city = new City(name, coordinates, area, population, metersAboveSeaLevel, capital, carCode, government, governor);
            city.setCreationDate(creationDate);
            city.setId(id);
            return city;
        }
    }

}
