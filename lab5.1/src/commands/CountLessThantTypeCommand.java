package commands;

import data.Dragon;
import data.DragonType;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import util.CollectionManager;
import util.Console;
import util.Interactor;

import java.time.LocalDateTime;
import java.util.Locale;

public class CountLessThantTypeCommand extends Command{
    private CollectionManager collectionManager;
    private Interactor interactor;


    public CountLessThantTypeCommand(CollectionManager collectionManager, Interactor interactor){
        super("count_less_than_type type","вывести количество элементов, значение" +
                " поля type которых меньше заданного");
        this.collectionManager = collectionManager;
        this.interactor = interactor;
    }


    @Override
    public boolean execute(String argument) {
        return false;
    }
}
