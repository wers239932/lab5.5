package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с площадью
 *
 * @author vladimir
 */
public class AreaException extends ArgumentCityException {
    public AreaException(String msg) {
        super(msg);
    }
}
