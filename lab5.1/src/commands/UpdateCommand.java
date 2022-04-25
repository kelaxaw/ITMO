package commands;

import data.*;
import exceptions.CollectionIsEmptyException;
import exceptions.DragonNotFoundException;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactor;

import java.time.LocalDateTime;

public class UpdateCommand extends Command{
    private final CollectionManager collectionManager;
    private final Interactor interactor;

    public UpdateCommand(CollectionManager collectionManager, Interactor interactor) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.collectionManager = collectionManager;
        this.interactor = interactor;
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

            String name = dragon.getName();
            Coordinates coordinates = dragon.getCoordinates();
            LocalDateTime creationDate = dragon.getCreationDate();
            Long age = dragon.getAge();
            long wingspan = dragon.getWingspan();
            DragonType type = dragon.getType();
            DragonCharacter character = dragon.getCharacter();
            DragonHead head = dragon.getHead();

            collectionManager.removeFromCollection(dragon);

            if (interactor.askQuestion("Хотите изменить имя Дракона?"))
                name = interactor.askName();
            if (interactor.askQuestion("Хотите изменить координаты дракона?"))
                coordinates = interactor.askCoordinates();
            if (interactor.askQuestion("Хотите изменить возраст?"))
                age = interactor.askAge();
            if (interactor.askQuestion("Хотите изменить размах крыльев?"))
                wingspan = interactor.askWingSpan();
            if (interactor.askQuestion("Хотите изменить тип дракона?"))
                type = interactor.askType();
            if (interactor.askQuestion("Хотите изменить характер дракона?"))
                character = interactor.askCharacter();
            if (interactor.askQuestion("Хотите изменить размер головы"))
                head = interactor.askDragonHead();

            collectionManager.addToCollection(new Dragon(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    age,
                    wingspan,
                    type,
                    character,
                    head
            ));
            Console.println("Дракон успешно изменена!");
            return true;
        } catch (WrongAmountOfArgumentsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (DragonNotFoundException exception) {
            Console.printerror("Группы с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {
            Console.printerror("Не удалось выполнить скрипт.");
        }
        return false;
    }
}
