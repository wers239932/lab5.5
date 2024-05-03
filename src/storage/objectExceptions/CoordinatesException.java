package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с координатами
 *
 * @author vladimir
 */
public class CoordinatesException extends ArgumentCityException {
    public CoordinatesException(String msg) {
        super(msg);
    }

}
