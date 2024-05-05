package сommands;

import cli.Command;
import storageInterface.StorageInterface;

import java.util.ArrayList;

public class CommandArrayFiller {
    static ArrayList<Command> commandList;

    public static ArrayList<Command> setBasicCommands(StorageInterface storage) {
        commandList = new ArrayList<>();
        commandList.add(new Add(storage));
        commandList.add(new Clear(storage));
        commandList.add(new CountGreaterThanCapital(storage));
        commandList.add(new Exit());
        commandList.add(new Info(storage));
        commandList.add(new RemoveAllByCarCode(storage));
        commandList.add(new RemoveById(storage));
        commandList.add(new RemoveFirst(storage));
        commandList.add(new RemoveGreater(storage));
        commandList.add(new RemoveLower(storage));
        commandList.add(new Show(storage));
        commandList.add(new SumOfCarCode(storage));
        commandList.add(new Update(storage));
        commandList.add(new Write());
        return commandList;
    }
}
