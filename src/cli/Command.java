package cli;

import api.Request;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.util.ArrayList;

public interface Command {
    ArrayList<String> execute(Request request, Storage storage) throws CommandException;

    String getName();

    String getDescription();

    Boolean getNeedObject();
}
