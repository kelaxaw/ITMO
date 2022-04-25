package commands;

import data.Dragon;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import exceptions.WrongAmountOfElementsException;
import util.CollectionManager;
import util.Console;
import util.Interactor;

import java.time.LocalDateTime;

public class AddCommand extends Command{
    private final CollectionManager collectionManager;
    private final Interactor interactor;


    public AddCommand(CollectionManager collectionManager, Interactor interactor) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.interactor = interactor;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            collectionManager.addToCollection(new Dragon(
                    collectionManager.generateNextId(),
                    interactor.askName(),
                    interactor.askCoordinates(),
                    LocalDateTime.now(),
                    interactor.askWingSpan(),
                    interactor.askAge(),
                    interactor.askType(),
                    interactor.askCharacter(),
                    interactor.askDragonHead()
                    )
            );
            Console.println("Дракон успешно добавлена!");
            return true;
        } catch (WrongAmountOfArgumentsException e){
            Console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {
            Console.printerror("Не удалось выполнить скрипт.");
        }
        return false;
    }
}
