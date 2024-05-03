package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с правительством
 *
 * @author vladimir
 */
public class GovernmentException extends ArgumentCityException {
    public GovernmentException(String msg) {
        super(msg);
    }
}
