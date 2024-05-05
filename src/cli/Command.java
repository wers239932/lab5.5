package cli;

import api.Request;
import cli.commandExceptions.CommandException;
import storage.Storage;

import java.io.Serializable;
import java.util.ArrayList;

public interface Command extends Serializable {
    ArrayList<String> execute(Request request, Storage storage) throws CommandException;

    String getName();

    String getDescription();

    Boolean getNeedObject();
}
