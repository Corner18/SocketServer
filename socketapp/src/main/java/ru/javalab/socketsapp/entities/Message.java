package ru.javalab.socketsapp.entities;

import java.time.LocalDateTime;


public class Message {
    private long sender_id;
    private LocalDateTime dateTime;
    private String text;

    public Message(long sender_id, LocalDateTime dateTime, String text){
        this.sender_id = sender_id;
        this.dateTime = dateTime;
        this.text = text;
    }

    public Message(){

    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
