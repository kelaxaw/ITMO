package run;

import commands.*;
import util.*;

import java.util.Scanner;

public class App {
    public static final String INPUT_COMMAND = "$ ";
    public static final String INPUT_INFO = "> ";


    public static void main(String[] args) {
        try(Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "LAB5";

            Interactor interactor = new Interactor(userScanner);
            FileManager fileManager = new FileManager(envVariable);

            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new AddCommand(collectionManager,interactor),
                    new HistoryCommand(),
                    new ExitCommand(),
                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new UpdateCommand(collectionManager,interactor),
                    new ExecuteScriptCommand(),
                    new RemoveByIdCommand(collectionManager),
                    new SaveCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new AddIfMaxCommand(collectionManager,interactor),
                    new AddIfMinCommand(collectionManager,interactor)

            );
            Console console = new Console(commandManager,userScanner,interactor);
            //console.interactiveMode();
           console.scriptMode("script.txt");
        }
    }
}
