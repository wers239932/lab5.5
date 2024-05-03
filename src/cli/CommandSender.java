package cli;

import api.Request;
import cli.commandExceptions.CommandDoesntExistException;
import cli.commandExceptions.CommandException;
import client.Client;
import client.InteractiveCityParser;
import storage.City;

import java.util.*;

public class CommandSender {
    private HashMap<String, Command> commandArray;
    private IOInterface terminal;
    private HashSet<String> runningScripts;
    private Client client;

    public CommandSender(IOInterface terminal) {
        this.terminal = terminal;
        this.commandArray = new HashMap<>();
        this.runningScripts = new HashSet<String>();
    }
    public CommandSender(IOInterface terminal, HashSet<String> runningScripts) {
        this.terminal = terminal;
        this.commandArray = new HashMap<>();
        this.runningScripts = runningScripts;
    }

    private Collection<Command> getCommandArray() {
        return new ArrayList<Command>(commandArray.values());
    }

    private void addCommandArray(Collection<Command> commandArrayList) {
        for (Command command : commandArrayList) {
            this.addCommand(command);
        }
    }


    public void start() {
        ArrayList<Command> commands = (ArrayList<Command>) this.client.sendRequest(new Request("getCommands")).getData();
        this.addCommandArray(commands);
        while (true) {
            try {
                ArrayList commandLine = new ArrayList(List.of(this.terminal.readLine().split(" +")));
                String commandName = (String) commandLine.get(0);
                ArrayList<String> response = new ArrayList<>();
                switch (commandName) {
                    case ("execute_script"): {
                        String filename = commandLine.get(1).toString();
                        if (this.runningScripts.contains(filename))
                            break;
                        FileTerminal fileIO = new FileTerminal(filename);
                        this.runningScripts.add(filename);
                        CommandSender commandExecuter = new CommandSender(fileIO, this.runningScripts);
                        commandExecuter.addCommandArray(this.getCommandArray());
                        commandExecuter.start();
                        this.runningScripts.remove(filename);
                        break;
                    }
                    default: {
                        City city = null;
                        Command command = this.getCommand(commandName);
                        if(command.getNeedObject()) {
                            city = InteractiveCityParser.parseCity(this.terminal);
                        }
                        commandLine.remove(0);
                        response = (ArrayList<String>) this.client.sendRequest(new Request<City>(commandName, city)).getData();
                        this.terminal.writeResponse(response);
                        break;
                    }
                }
                //this.terminal.writeResponse(response);
            } catch (CommandDoesntExistException e) {
                this.terminal.writeLine("такой команды не существует");
            } catch (NullPointerException e) {
                this.terminal.writeLine("команда возвращает null набор строк");
            } catch (CommandException e) {
                this.terminal.writeLine(e.getMessage());
            } catch (NoSuchElementException e) {
                return;
            } catch (Exception e) {
                terminal.writeLine(e.getMessage() + "\n" + e.getClass());
            }

        }
    }


    public void addCommand(Command command) {
        this.commandArray.put(command.getName(), command);
    }

    private Command getCommand(Object name) throws CommandDoesntExistException {

        Command command = this.commandArray.get((String) name);
        if (command == null) throw new CommandDoesntExistException();
        return command;

    }

    /*public ArrayList<String> help() {
        ArrayList<String> response = new ArrayList<>();
        for (Command command : this.commandArray.values()) {
            response.add(command.getDescription());
        }
        return response;
    }*/
}
