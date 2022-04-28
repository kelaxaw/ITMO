package run;

import commands.*;
import exceptions.IncorrectInputInScriptException;
import util.*;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/**
 * главный класс, в котором создаются объекты и запускается приложение
 */
public class App {
    public static final String INPUT_COMMAND = "$ ";
    public static final String INPUT_INFO = "> ";


    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "LAB5";

            Interactor interactor = new Interactor(userScanner);
            FileManager fileManager = new FileManager(envVariable);

            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new AddCommand(collectionManager, interactor),
                    new HistoryCommand(),
                    new ExitCommand(),
                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new UpdateCommand(collectionManager, interactor),
                    new ExecuteScriptCommand(),
                    new RemoveByIdCommand(collectionManager),
                    new SaveCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new AddIfMaxCommand(collectionManager, interactor),
                    new AddIfMinCommand(collectionManager, interactor)
            );
            Console console = new Console(commandManager, userScanner, interactor);

            System.out.println("\nВведите цифру соответствующую нужному вам режиму работы программы \n 1 - interactive \n 2 - script");

            while (true) {

                String inputAnswer = userScanner.nextLine().trim();

                if (Objects.equals(inputAnswer, "1")) {
                    console.interactiveMode();
                } else if (Objects.equals(inputAnswer, "2")) {
                    console.scriptMode("script.txt");
                }

            }
        }
    }
}
