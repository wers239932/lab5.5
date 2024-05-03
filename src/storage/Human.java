package storage;

import storage.objectExceptions.GovernorException;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Класс всего полезного о человеке - дата рождения
 */

public class Human implements Serializable {
    private final LocalDateTime birthday;

    public static Human parseGovernor(String governor) throws GovernorException {
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(governor);
        } catch (Exception e) {
            throw new GovernorException("не удалось преобразовать строку в дату");
        }
        return new Human(date);
    }

    public Human(LocalDateTime date) {
        this.birthday = date;
    }

    @Override
    public String toString() {
        return this.birthday.toString();
    }
}
