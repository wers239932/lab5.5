package api;

import java.io.Serializable;

/**
 * класс запроса клиента для отправки по сети
 *
 * @param <T>
 */

public class Request<T> implements Serializable {

    /**
     * название команды серверу
     */
    private String commandName;
    /**
     * информация для запроса
     */
    private T data;

    public Request(String commandName, T data) {
        this.commandName = commandName;
        this.data = data;
    }

    public Request(String commandName) {
        this.commandName = commandName;
        this.data = null;
    }

    public String getCommandName() {
        return commandName;
    }

    public T getData() {
        return data;
    }
}