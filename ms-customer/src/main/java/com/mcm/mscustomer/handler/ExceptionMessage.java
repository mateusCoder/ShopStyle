package com.mcm.mscustomer.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionMessage {

    private String timestamp;
    private String code;
    private String error;
    private String message;
    private String path;
    private String status;

    @Override
    public String toString(){
        return "Exception Message [timestamp=" + timestamp + ", code=" + code + ", error=" + error +
                ", message=" + message + ", path=" + path + ", status=" + status + "]";
    }
}
