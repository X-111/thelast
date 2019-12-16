package com.htt.pojo;

import java.util.ArrayList;

public class message {
    private String sender;
    ArrayList<amessage> message1;

    public ArrayList<amessage> getMessage1() {
        return message1;
    }

    public String getSender() {
        return sender;
    }

    public void setMessage1(ArrayList<amessage> message1) {
        this.message1 = message1;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
