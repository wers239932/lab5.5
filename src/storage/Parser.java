package storage;

import cli.ArgumentParser;
import cli.IOInterface;
import cli.commandExceptions.CommandException;

public class Parser<T> {
    T t;

    public T getArgumentWithRules(String msg, IOInterface terminal, ArgumentParser parser) throws CommandException {
        String arg = "";
        if (terminal.isInteractive()) {
            terminal.writeLine(msg);
        }
        while (true) {
            try {
                t = (T) parser.parse(terminal.readLine());
                break;
            } catch (Exception e) {
                if (!terminal.isInteractive())
                    throw new CommandException("неправильный аргумент, не могу перечитывать в интерактивном режиме");
                terminal.writeLine(e.getMessage());
                terminal.writeLine("некорректный ввод, повторите заново");
                terminal.writeLine(msg);
            }
        }
        return t;

    }
}
