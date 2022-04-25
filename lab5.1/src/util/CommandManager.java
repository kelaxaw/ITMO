package util;

import commands.Command;
import exceptions.HistoryIsEmptyException;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 12;
    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands =new ArrayList<>();
    private final Command addCommand;
    private final Command historyCommand;
    private final Command exitCommand;
    private final Command helpCommand;
    private final Command infoCommand;
    private final Command showCommand;
    private final Command updateCommand;
    private final Command executeScriptCommand;
    private final Command removeByIdCommand;
    private final Command saveCommand;
    private final Command clearCommand;
    private final Command addIfMaxCommand;
    private final Command addIfMinCommand;







    public CommandManager (Command addCommand,Command historyCommand,Command exitCommand,Command helpCommand,
                           Command infoCommand,Command showCommand,Command updateCommand, Command executeScriptCommand,
                           Command removeByIdCommand, Command saveCommand, Command clearCommand,
                           Command addIfMaxCommand, Command addIfMinCommand){
        this.addCommand = addCommand;
        this.historyCommand = historyCommand;
        this.exitCommand = exitCommand;
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.updateCommand = updateCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.saveCommand = saveCommand;
        this.clearCommand = clearCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.addIfMinCommand = addIfMinCommand;





        commands.add(addCommand);
        commands.add(exitCommand);
        commands.add(helpCommand);
        commands.add(historyCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(updateCommand);
        commands.add(executeScriptCommand);
        commands.add(removeByIdCommand);
        commands.add(saveCommand);
        commands.add(clearCommand);
        commands.add(addIfMaxCommand);
        commands.add(addIfMinCommand);
    }

    public List<Command> getCommands(){
        return commands;
    }

    public boolean NoSuchCommand(String command){
        Console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }
    public boolean add(String argument){
        return addCommand.execute(argument);
    }
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }
    public boolean executeScript(String argument) {return executeScriptCommand.execute(argument);}
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }
    public boolean save(String argument) {return saveCommand.execute(argument);}
    public boolean addIfMax(String argument) {return addIfMaxCommand.execute(argument);}
    public boolean clear(String argument) {return clearCommand.execute(argument);}
    public boolean addIfMin(String argument) {return addIfMinCommand.execute(argument);}




    public boolean exit(String argument){
        return exitCommand.execute(argument);
    }
    public boolean help(String argument){
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                Console.printtable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }


    public String[] getCommandHistory(){
        return commandHistory;
    }

    public void addToHistory(String commandToStore){
        for (Command command : commands){
            if (command.getName().split(" ")[0].equals(commandToStore)){
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--){
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    public boolean history(String argument) {
        if (historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new HistoryIsEmptyException();

                Console.println("Последние использованные команды:");
                for (int i=0; i<commandHistory.length; i++) {
                    if (commandHistory[i] != null) Console.println(" " + commandHistory[i]);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Console.println("Ни одной команды еще не было использовано!");
            }
        }
        return false;
    }

}
