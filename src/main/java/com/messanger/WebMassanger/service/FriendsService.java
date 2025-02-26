package com.messanger.WebMassanger.service;

import com.messanger.WebMassanger.model.Friends;
import com.messanger.WebMassanger.repository.FriendsRepository;
import com.messanger.WebMassanger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {
  private final UserRepository userRepository;
  private final FriendsRepository friendsRepository;
  private final UserService userService;

    public FriendsService(UserRepository userRepository, FriendsRepository friendsRepository,UserService userService) {
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
        this.userService = userService;
    }

    public Friends addFriend(String friend_name,String my_name){
        Friends friends = new Friends();
        friends.setfriend_name(friend_name);
        friends.setMy_name(my_name);
        return friendsRepository.save(friends);
    }
    public boolean AreFriends(String friend_name,String my_name){

    if(userService.isNameExists(friend_name)&&userService.isNameExists(my_name)){
        if(friendsRepository.findByMyNameAndFriendName(my_name,friend_name)==null) {
            if(friendsRepository.findByMyNameAndFriendName(friend_name,my_name)==null) {
                return false;
            }
        }
    }

    return true;
    }
    public List<String> myFriends(String myName){
        List<String> friends=new ArrayList<>();
        List<Friends>foundFriends= friendsRepository.findAllByFriendNameOrMyName(myName,myName);
        for (Friends f : foundFriends){
            if(f.getfriend_name().equals(myName)){
                friends.add(f.getMy_name());
            }
            else{
                friends.add(f.getfriend_name());
            }

        }

        return friends;
    }


}
