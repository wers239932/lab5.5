package storage;

import java.io.Serializable;

public class CarCode implements Serializable {
    private Long carCode;

    public CarCode(Long carCode) {
        this.carCode = carCode;
    }

    public Long getCarCode() {
        return carCode;
    }

    public void setCarCode(Long carCode) {
        this.carCode = carCode;
    }
}
