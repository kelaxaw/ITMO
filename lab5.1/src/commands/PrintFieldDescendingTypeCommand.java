package commands;

import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;

public class PrintFieldDescendingTypeCommand extends Command{
    private CollectionManager collectionManager;

    public PrintFieldDescendingTypeCommand(CollectionManager collectionManager) {
        super("print_field_descending_type", " вывести значения поля type всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            System.out.println("значения поля type всех элементов в порядке убывания: " + collectionManager.sortedByType());
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            System.out.println("У этой команды нет параметров!");
        }
        return false;
    }
}
