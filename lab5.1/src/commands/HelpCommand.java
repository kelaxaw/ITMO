package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.Console;

public class HelpCommand extends Command{
    public HelpCommand(){
        super("help", "вывести справку по доступным командам");
    }


    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
