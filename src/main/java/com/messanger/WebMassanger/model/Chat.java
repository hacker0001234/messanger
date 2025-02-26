package com.messanger.WebMassanger.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Chat {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
long Id;

@Column(nullable = false)
String myName;

@Column(nullable = false)
String friendName;

@Column(nullable = false)
String message;

@Column(nullable = false)
String messageOwner;

@Column(nullable = false)
private LocalDateTime timestamp;

    // Конструктор для створення нового повідомлення
    public Chat(String myName, String friendName, String message, String messageOwner) {
        this.myName = myName;
        this.friendName = friendName;
        this.message = message;
        this.messageOwner = messageOwner;
        this.timestamp = LocalDateTime.now();
    }
    public Chat(){}

    public long getId() {
        return Id;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageOwner() {
        return messageOwner;
    }

    public String getMyName() {
        return myName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageOwner(String messageOwner) {
        this.messageOwner = messageOwner;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
