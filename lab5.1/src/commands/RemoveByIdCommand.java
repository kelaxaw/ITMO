package commands;

import data.Dragon;
import exceptions.CollectionIsEmptyException;
import exceptions.DragonNotFoundException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;

public class RemoveByIdCommand extends Command{
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command execute status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            Dragon dragon = collectionManager.getById(id);
            if (dragon == null) throw new DragonNotFoundException();
            collectionManager.removeFromCollection(dragon);
            Console.println("Дракон успешно удалена!");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (DragonNotFoundException exception) {
            Console.printerror("Группы с таким ID в коллекции нет!");
        }
        return false;
    }
}
