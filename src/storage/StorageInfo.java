package storage;

import java.io.Serializable;
import java.util.Date;

public class StorageInfo implements Serializable {
    private Date creationDate;
    private int size;

    public StorageInfo(int size, Date creationDate) {
        this.size = size;
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getSize() {
        return size;
    }
}
