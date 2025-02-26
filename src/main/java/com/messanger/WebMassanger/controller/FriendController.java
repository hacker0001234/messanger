package com.messanger.WebMassanger.controller;

import com.messanger.WebMassanger.model.Friends;
import com.messanger.WebMassanger.repository.FriendsRepository;
import com.messanger.WebMassanger.repository.UserRepository;
import com.messanger.WebMassanger.service.FriendsService;
import com.messanger.WebMassanger.service.UserService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FriendController {
 private final FriendsService friendsService;
 private final UserService userService;

    public FriendController(FriendsService friendsService, UserService userService) {
        this.friendsService = friendsService;
        this.userService = userService;
    }

    @GetMapping("/addfriend")
    public String addFriend(){
        return "addFriend";
    }
    @PostMapping("/addingfriend")
    public String addingFriend(@RequestParam("friendName")String friendName, OAuth2AuthenticationToken authentication, Model model){
        String myname = userService.myname(authentication);

        if(!friendsService.AreFriends(friendName,myname)){
            friendsService.addFriend(friendName,myname);

            return "redirect:/messenger";
        }
            return "redirect:/error_friend";
    }
  @GetMapping("/error_friend")
    public String errorFriend()
    {
        return "error_friend";
    }

}
