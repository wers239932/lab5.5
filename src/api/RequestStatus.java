package api;

import java.io.Serializable;

public enum RequestStatus implements Serializable {
    DONE("done"),
    FAILED("failed");

    RequestStatus(String failed) {
    }
}
