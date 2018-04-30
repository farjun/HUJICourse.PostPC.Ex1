package com.example.omer.hujicoursepostpc;


import java.util.Date;
import java.util.SimpleTimeZone;
public class Message {

    private String username;
    private String msg;
    private String time;


    Message(String username , String msg , Date timeStamp){
        this.username = username;
        this.msg = msg;
        this.time = timeStamp.toString();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }





}
