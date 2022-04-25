package util;

import exceptions.ScriptRecursionException;
import run.App;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private final CommandManager commandManager;
    private final Scanner userScanner;
    private final Interactor interactor;
    private final List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner userScanner, Interactor interactor) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.interactor = interactor;
    }

   public void interactiveMode(){
       String[] userCommand = {"", ""};
       int commandStatus;
       try {
           do {
               Console.print(App.INPUT_COMMAND);
               userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
               userCommand[1] = userCommand[1].trim();
               commandManager.addToHistory(userCommand[0]);
               commandStatus = launchCommand(userCommand);
           } while (commandStatus != 2);
       } catch (NoSuchElementException exception) {
           Console.printerror("Пользовательский ввод не обнаружен!");
       } catch (IllegalStateException exception) {
           Console.printerror("Непредвиденная ошибка!");
       }
   }

    public int scriptMode(String argument){
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = interactor.getUserScanner();
            interactor.setUserScanner(scriptScanner);
            interactor.setFileMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(App.INPUT_COMMAND + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            interactor.setUserScanner(tmpScanner);
            interactor.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute") && !userCommand[1].isEmpty()))
                Console.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printerror("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printerror("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            Console.printerror("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    private int launchCommand(String[] userCommand){
        String command = userCommand[0];
        String argument = userCommand[1];
        switch (command){
            case "":
                break;
            case "help":
                if (!commandManager.help(argument)) return 1;
                break;
            case "info":
                if (!commandManager.info(argument)) return 1;
                break;
            case "show":
                if (!commandManager.show(argument)) return 1;
                break;
            case "update":
                if (!commandManager.update(argument)) return 1;
                break;
            case "add":
                if (!commandManager.add(argument)) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(argument)) return 1;
                break;
            case "save":
                if (!commandManager.save(argument)) return 1;
                break;
            case "add_if_max":
                if (!commandManager.addIfMax(argument)) return 1;
                break;
            case "add_if_min":
                if (!commandManager.addIfMin(argument)) return 1;
                break;
            case "execute":
                if (!commandManager.executeScript(argument)) return 1;
                else return scriptMode(argument);
            case "history":
                if (!commandManager.history(argument)) return 1;
                break;
            case "clear":
                if (!commandManager.clear(argument)) return 1;
                break;
            case "exit":
                if (!commandManager.exit(argument)) return 1;
                else return 2;
            default:
                if (!commandManager.NoSuchCommand(command)) return 1;
        }
        return 0;
    }

    public static void print(Object toOut) {System.out.print(toOut);}
    public static void println(Object toOut) {System.out.println(toOut);}
    public static void printerror(Object toOut) {System.out.println("error: " + toOut);}
    public static void printtable(Object element1, Object element2) {System.out.printf("%-37s%-1s%n", element1, element2);}

    @Override
    public String toString() {
        return "Console (класс для обработки ввода команд)";
    }
}
