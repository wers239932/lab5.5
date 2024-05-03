package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с мэром
 *
 * @author vladimir
 */
public class GovernorException extends ArgumentCityException {
    public GovernorException(String msg) {
        super(msg);
    }
}
