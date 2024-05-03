package storage.objectExceptions;

/**
 * Абстрактный класс обибок при вводе неверных типов данных при создании объекта типа City
 *
 * @author vladimir
 */

public abstract class ArgumentCityException extends Exception {
    public ArgumentCityException(String msg) {
        super(msg);
    }
}
