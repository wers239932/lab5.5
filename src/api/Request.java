package api;

import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<String> args;

    public Request(String commandName, T data, ArrayList<String> args) {
        this.commandName = commandName;
        this.data = data;
        this.args = args;
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

    public ArrayList<String> getArgs() {
        return this.args;
    }
}