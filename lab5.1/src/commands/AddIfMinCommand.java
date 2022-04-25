package commands;

import data.Dragon;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactor;

import java.time.LocalDateTime;

public class AddIfMinCommand extends Command{
    private CollectionManager collectionManager;
    private Interactor interactor;


    public AddIfMinCommand(CollectionManager collectionManager, Interactor interactor){
        super("add_if_min {element}","добавить новый элемент в коллекцию, если его значение" +
                " меньше, чем у наименьшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.interactor = interactor;
    }


    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Dragon dragon = new Dragon(
                    collectionManager.generateNextId(),
                    interactor.askName(),
                    interactor.askCoordinates(),
                    LocalDateTime.now(),
                    interactor.askAge(),
                    interactor.askWingSpan(),
                    interactor.askType(),
                    interactor.askCharacter(),
                    interactor.askDragonHead()
            );
            if (collectionManager.collectionSize() == 0 || dragon.compareTo(collectionManager.getFirst()) < 0) {
                collectionManager.addToCollection(dragon);
                Console.println("Дракон успешно добавлен!");
                return true;
            } else Console.printerror("Значение дракона больше, чем значение наименьшего из дракона!");
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Использование: '" + getName() + "'");
        }catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (IncorrectInputInScriptException exception) {
            Console.printerror("Не удалось выполнить скрипт.");
        }
        return false;
    }
}
