package storage;

import java.io.Serializable;

public class ID implements Serializable {
    private int id;

    public ID(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
