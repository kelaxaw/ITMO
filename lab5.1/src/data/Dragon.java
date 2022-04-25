package data;

import java.time.LocalDateTime;
import java.util.Objects;

public class Dragon implements Comparable<Dragon> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private long wingspan; //Значение поля должно быть больше 0
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле не может быть null
    private DragonHead head;

    public Dragon(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, Long age, long wingspan, DragonType type,
                  DragonCharacter character, DragonHead head){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.wingspan = wingspan;
        this.type = type;
        this.character = character;
        this.head = head;
    }

    public Integer getId(){return id;}
    public String getName(){return name;}
    public Coordinates getCoordinates(){return coordinates;}
    public LocalDateTime getCreationDate(){return creationDate;}
    public Long getAge(){return age;}
    public long getWingspan(){return wingspan;}
    public DragonHead getHead(){return head;}
    public DragonType getType(){return type;}
    public DragonCharacter getCharacter(){return character;}

    public void setId( Integer id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setCoordinates(Coordinates coordinates){this.coordinates = coordinates;}
    public void setCreationDate(LocalDateTime creationDate){this.creationDate = creationDate;}
    public void setAge(Long age){this.age= age;}
    public void setWingspan(long wingspan){this.wingspan = wingspan;}
    public void setHead(DragonHead head){this.head = head;}
    public void setType(DragonType type){this.type = type;}
    public void setCharacter(DragonCharacter character){this.character = character;}


    @Override
    public int compareTo(Dragon dragonObj) {
        return id.compareTo(dragonObj.getId());
    }
    @Override
    public String toString() {
        String info = "";
        info += "Дракон №" + id;
        info += " (добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Возраст: " + age;
        info += "\n Размах крыльев: " + wingspan;
        info += "\n Тип: " + type;
        info += "\n Размер головы: " + head;
        info += "\n Характер: " + character;
        return info;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, age, wingspan, type, head, character);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Dragon) {
            Dragon dragonObj = (Dragon) obj;
            return name.equals(dragonObj.getName()) && coordinates.equals(dragonObj.getCoordinates()) &&
                    (age == dragonObj.getAge()) && (wingspan == dragonObj.getWingspan()) &&
                    (type == dragonObj.getType()) && (head == dragonObj.getHead()) &&
                    character.equals(dragonObj.getCharacter());
        }
        return false;
    }

}
