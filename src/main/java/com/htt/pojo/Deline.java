package com.htt.pojo;

public class Deline {
    private  String receiver;
    private  long  lasttime;
    private String sender;
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }


    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public String getReceiver() {
        return receiver;
    }

    public long getLasttime() {
        return lasttime;
    }
}

