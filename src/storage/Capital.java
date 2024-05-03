package storage;

import java.io.Serializable;

public class Capital implements Serializable {
    Boolean capital;

    public Capital(Boolean capital) {
        this.capital = capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public Boolean getCapital() {
        return capital;
    }
}
