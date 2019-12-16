package com.htt.pojo;

public class messageDownEnty {
    private String sender;
    private String receiver;
    private String message;
    private String time1;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }
    public String getBystring(String a){
        switch (a){
            case "sender":{
                return this.sender;
            }
            case  "receiver":{
                return  this.receiver;
            }
            case "time":{
                return  this.time1;
            }
            case "message":{
                return  this.message;
            }

        }
    return  "error";
    }
}
