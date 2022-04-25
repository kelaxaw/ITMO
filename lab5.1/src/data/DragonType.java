package data;

public enum DragonType {
    WATER,
    UNDERGROUND,
    AIR,
    FIRE;

    public static String nameList() {
        String nameList = "";
        for (DragonType dragonType : values()) {
            nameList += dragonType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
