package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с площадью
 *
 * @author vladimir
 */
public class IdException extends ArgumentCityException {
    public IdException(String msg) {
        super(msg);
    }
}
