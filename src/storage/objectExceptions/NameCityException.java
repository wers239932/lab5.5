package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с названием города
 *
 * @author vladimir
 */

public class NameCityException extends ArgumentCityException {

    public NameCityException(String msg) {
        super(msg);
    }
}
