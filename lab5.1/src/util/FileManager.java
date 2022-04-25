package util;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import data.Dragon;
import exceptions.NoAccessToFileException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

public class FileManager {
    private Gson gson = new Gson();
    private String envVariable;

    public FileManager(String envVariable){
        this.envVariable = envVariable;
    }



    private BufferedInputStream getBufferedInputStream() throws FileNotFoundException, NoAccessToFileException{
        File file = new File(System.getenv(envVariable));
        if (file.exists() && !file.canRead()) throw new NoAccessToFileException();
        return new BufferedInputStream(new FileInputStream(file));
    }

    private BufferedWriter getBufferedWriter() throws IOException, NoAccessToFileException {
        File file = new File(System.getenv(envVariable));
        if (file.exists() && !file.canWrite()) throw new NoAccessToFileException();
        return new BufferedWriter(new FileWriter(new File(System.getenv(envVariable))));
    }





    public void writeCollection(Collection<Dragon> collection){
        if (System.getenv().get(envVariable) != null) {
            try (PrintWriter collectionFileWriter = new PrintWriter(new File(System.getenv().get(envVariable)))) {
                collectionFileWriter.write(gson.toJson(collection));
                Console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                Console.printerror("Загрузочный файл является директорией/не может быть открыт!");
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
    }



   public TreeSet<Dragon> readCollection(){
        if (System.getenv().get(envVariable) != null){
            try (Scanner collectionFileScanner = new Scanner(new File(System.getenv().get(envVariable)))) {
                TreeSet<Dragon> collection;
                Type collectionType = new TypeToken<TreeSet<Dragon>>() {}.getType();
                collection = gson.fromJson(collectionFileScanner.nextLine().trim(), collectionType);
                return collection;
            } catch (FileNotFoundException exception) {
                Console.printerror("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                Console.println("Загрузочный файл пуст!");
            } catch (JsonParseException | NullPointerException exception) {
                Console.printerror("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
//                System.exit(0);
            }
        } else Console.printerror("Системная переменная с загрузочным файлом не найдена!");
       Console.println("Проверьте переменную окружения " + envVariable + " и запустите заново.");
//       System.exit(0);
       return new TreeSet<>();
    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}
