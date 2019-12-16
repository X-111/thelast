package com.htt.pojo;

public class info {
    private String uname;
    private  String age;
    private String address;
    private String instruction;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
