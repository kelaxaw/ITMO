package util;

import data.*;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import run.App;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Interactor {
    private final long MIN_WINGSPAN = 0;
    private final long MIN_AGE = 0;
    private final float MAX_X = 994;
    private final Integer MAX_Y = 276;

    private Scanner userScanner;
    private boolean fileMode;

    public Interactor(Scanner userScanner){
        this.userScanner = userScanner;
        fileMode = false;
    }

    public void setUserScanner(Scanner userScanner) {this.userScanner = userScanner;}
    public Scanner getUserScanner() {return userScanner;}
    public void setFileMode() {fileMode = true;}
    public void setUserMode() {fileMode = false;}

    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите имя:");
                Console.print(App.INPUT_INFO);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }
    public float askX() throws IncorrectInputInScriptException {
        String strX;
        float x;
        while (true) {
            try {
                Console.println("Введите координату X < " + (MAX_X+1) + ":");
                Console.print(App.INPUT_INFO);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Float.parseFloat(strX);
                if (x > MAX_X) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Координата X не может превышать " + MAX_X + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }
    public Integer askY() throws IncorrectInputInScriptException {
        String strY;
        Integer y;
        while (true) {
            try {
                Console.println("Введите координату Y < " + (MAX_Y+1) + ":");
                Console.print(App.INPUT_INFO);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Integer.parseInt(strY);
                if (y > MAX_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            } catch (NotInDeclaredLimitsException e) {
                Console.printerror("Координата Y не может превышать " + MAX_Y + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }
        }
        return y;
    }
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        float x = askX();
        Integer y = askY();
        return new Coordinates(x, y);
    }

    public long askWingSpan() throws IncorrectInputInScriptException {
        String strWingSpan;
        long wingspan;
        while (true) {
            try {
                Console.println("Введите размах крыльев:");
                Console.print(App.INPUT_INFO);
                strWingSpan = userScanner.nextLine().trim();
                if (fileMode) Console.println(strWingSpan);
                wingspan = Long.parseLong(strWingSpan);
                if (wingspan < MIN_WINGSPAN) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Размах крыльев не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Размах крыльев должен быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Размах крыльев должен быть представлен числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return wingspan;
    }
    public Long askAge() throws IncorrectInputInScriptException {
        String strAge;
        Long age;
        while (true) {
            try {
                Console.println("Введите возраст:");
                Console.print(App.INPUT_INFO);
                strAge = userScanner.nextLine().trim();
                if (fileMode) Console.println(strAge);
                age = Long.parseLong(strAge);
                if (age < MIN_AGE) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Возраст не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Возраст должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Возраст должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return age;
    }
    public DragonType askType() throws IncorrectInputInScriptException{
        String strType;
        DragonType type;
        while (true) {
            try {
                Console.println("Список типов - " + DragonType.nameList());
                Console.println("Введите тип:");
                Console.print(App.INPUT_INFO);
                strType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strType);
                type = DragonType.valueOf(strType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Тип не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Тип нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return type;
    }
    public DragonCharacter askCharacter() throws IncorrectInputInScriptException{
        String strCharacter;
        DragonCharacter character;
        while (true) {
            try {
                Console.println("Список характеров - " + DragonCharacter.nameList());
                Console.println("Введите характер:");
                Console.print(App.INPUT_INFO);
                strCharacter = userScanner.nextLine().trim();
                if (fileMode) Console.println(strCharacter);
                character = DragonCharacter.valueOf(strCharacter.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Характер не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Характера нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return character;
    }
    public double askDragonHeadSize() throws IncorrectInputInScriptException {
        String strDragonHeadSize;
        double size;
        while (true) {
            try {
                Console.println("Введите размер головы дракона :");
                Console.print(App.INPUT_INFO);
                strDragonHeadSize = userScanner.nextLine().trim();
                if (fileMode) Console.println(strDragonHeadSize);
                size = Double.parseDouble(strDragonHeadSize);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Размер головы не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }  catch (NumberFormatException exception) {
                Console.printerror("Размер головы должен быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return size;
    }
    public DragonHead askDragonHead() throws IncorrectInputInScriptException {
        double size;
        size = askDragonHeadSize();
        return new DragonHead(size);
    }
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.INPUT_INFO);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }

}
