package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с carCode
 *
 * @author vladimir
 */
public class CarCodeException extends ArgumentCityException {
    public CarCodeException(String msg) {
        super(msg);
    }
}
