package сommands;

import cli.Command;
import cli.commandExceptions.CommandDoesntExistException;
import storageInterface.StorageInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandArrayFiller {
    static ArrayList<Command> commandList;

    public static ArrayList<Command> setBasicCommands() {
        commandList = new ArrayList<>();
        commandList.add(new Help());
        commandList.add(new Add());
        commandList.add(new Clear());
        commandList.add(new CountGreaterThanCapital());
        commandList.add(new Exit());
        commandList.add(new Info());
        commandList.add(new RemoveAllByCarCode());
        commandList.add(new RemoveById());
        commandList.add(new RemoveFirst());
        commandList.add(new RemoveGreater());
        commandList.add(new RemoveLower());
        commandList.add(new Show());
        commandList.add(new SumOfCarCode());
        commandList.add(new Update());
        commandList.add(new Write());
        return commandList;
    }
    public static HashMap<String, Command> getCommandMap() {
        HashMap<String, Command> commandMap = new HashMap<>();
        for(Command command : commandList)
            commandMap.put(command.getName(), command);
        return commandMap;
    }
}
