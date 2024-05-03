package storage.objectExceptions;

/**
 * Класс расширяющий ArgumentCityException означающий ошибку с населением
 *
 * @author vladimir
 */
public class PopulationException extends ArgumentCityException {

    public PopulationException(String msg) {
        super(msg);
    }


}
