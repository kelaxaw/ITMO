package util;

import data.Dragon;
import data.DragonType;

import java.time.LocalDateTime;
import java.util.*;

public class CollectionManager {
    private NavigableSet<Dragon> myCollection = new TreeSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    public CollectionManager(FileManager fileManager){
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        loadCollection();
    }

    public NavigableSet<Dragon> getCollection(){return myCollection;}
    public LocalDateTime getLastInitTime(){return lastInitTime;}
    public LocalDateTime getLastSaveTime(){return lastSaveTime;}
    public String collectionType(){return myCollection.getClass().getName();}
    public int collectionSize(){return myCollection.size();}
    public Dragon getFirst(){
        if (myCollection.isEmpty()) return null;
        return myCollection.first();
    }
    public Dragon getLast(){
        if (myCollection.isEmpty()) return null;
        return myCollection.last();
    }
    public Dragon getById(Integer id){
        for (Dragon dragon : myCollection){
            if (dragon.getId().equals(id)) return dragon;
        }
        return null;
    }
    public Dragon getByValue(Dragon dragonToFind) {
        for (Dragon dragon : myCollection) {
            if (dragon.equals(dragonToFind)) return dragon;
        }
        return null;
    }

//    public int countLessThanType(DragonType type) {
//        int count =0;
//        for (ListIterator<Dragon> iterator =  ;iterator.hasNext(); ) {
//            Dragon dragon = iterator.next();
//            if (dragon.getType() != null && dragon.getType().compareTo(type) < 0) {
//                count += 1;
//            }
//        }
//        return count;
//    }

    public ArrayList<DragonType> sortedByType(){
        ArrayList<DragonType> arrayList = new ArrayList<DragonType>();
        for (Dragon dragon : myCollection){
            arrayList.add(dragon.getType());
        }
        Collections.sort(arrayList);
        Collections.reverse(arrayList);
        return arrayList;
    }





//    ЕЩЕ МЕТОДЫ ДЛЯ КОМАНД!!!!!


    public void addToCollection(Dragon dragon){myCollection.add(dragon);}
    public void removeFromCollection(Dragon dragon){myCollection.remove(dragon);}
    public void clearCollection(){myCollection.clear();}

    public Integer generateNextId(){
        if (myCollection.isEmpty()) return 1;
        return myCollection.last().getId() + 1;
    }
    public void saveCollection(){
        fileManager.writeCollection(myCollection);
        lastSaveTime = LocalDateTime.now();
    }
    private void loadCollection(){
        myCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (myCollection.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (Dragon dragon : myCollection) {
            info += dragon;
            if (dragon != myCollection.last()) info += "\n\n";
        }
        return info;
    }
}
