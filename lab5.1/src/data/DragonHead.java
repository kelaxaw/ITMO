package data;

import java.util.Objects;

public class DragonHead {
    private double size;

    public DragonHead(double size){
        this.size = size;
    }
    public void setSize(double size){this.size = size;}
    public double getSize(){return size;}

    @Override
    public String toString() {
        return "Head Size" + size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof DragonHead) {
            DragonHead dragonHeadObj = (DragonHead) obj;
            return (size == dragonHeadObj.getSize());
        }
        return false;
    }
}
