package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку со столицей
 *
 * @author vladimir
 */
public class CapitalException extends ArgumentCityException {
    public CapitalException(String msg) {
        super(msg);
    }
}
