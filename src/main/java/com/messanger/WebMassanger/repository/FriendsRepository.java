package com.messanger.WebMassanger.repository;

import com.messanger.WebMassanger.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FriendsRepository extends JpaRepository<Friends,Long> {

Friends findByFriendName (String friend_name);
Friends findByMyName(String myName);
Friends findByMyNameAndFriendName(String myName,String friendName);
Friends findAllByMyName(String myName);
Friends findAllByFriendName(String friendName);
List<Friends> findAllByFriendNameOrMyName(String friendName, String myName);
}