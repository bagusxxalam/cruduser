package net.blueapple.presentation;

import java.io.Serializable;

/**
 * Created by Lithium on 3/26/2017.
 */
public class RestResponse implements Serializable {
    private int code;
    private String message;
    private Object detail;

    public RestResponse() {}
    public RestResponse(int code, String message, Object detail) {
        this.code=code;
        this.message=message;
        this.detail=detail;
    }
    public RestResponse(int code, String message) {
        this.code=code;
        this.message=message;
        this.detail=null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
