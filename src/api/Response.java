package api;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private T data;
    private String error;
    private final RequestStatus requestStatus;

    public String getError() {
        return error;
    }

    public Response(T data, RequestStatus status, String error) {
        this.data = data;
        this.requestStatus = status;
        if (error != null) this.error = error;
    }

    public Response(RequestStatus status, String error) {
        this.requestStatus = status;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public RequestStatus getRequestStatus() {
        return this.requestStatus;
    }
    public Boolean isDone()
    {
        return this.requestStatus.equals(RequestStatus.DONE);
    }
}
