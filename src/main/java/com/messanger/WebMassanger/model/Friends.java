package com.messanger.WebMassanger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String friendName;

    @Column(nullable = false)
    private String myName;

    public boolean contains(String user1, String user2) {
        return (friendName.equals(user1) && myName.equals(user2)) ||
                (friendName.equals(user2) && myName.equals(user1));
    }

    public Long getId() {
        return id;
    }

    public String getfriend_name() {
        return friendName;
    }

    public void setfriend_name(String friend_name) {
        this.friendName = friend_name;
    }

    public String getMy_name() {
        return myName;
    }

    public void setMy_name(String my_name) {
        this.myName = my_name;
    }
}
