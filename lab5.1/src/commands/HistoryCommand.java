package commands;

import exceptions.WrongAmountOfArgumentsException;

public class HistoryCommand extends Command{
    public HistoryCommand() {
        super("history", "вывести историю использованных команд");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            System.out.println("У этой команды нет параметров!");
        }
        return false;
    }
}
