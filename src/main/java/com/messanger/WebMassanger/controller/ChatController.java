package com.messanger.WebMassanger.controller;

import com.messanger.WebMassanger.model.Chat;
import com.messanger.WebMassanger.repository.FriendsRepository;
import com.messanger.WebMassanger.service.ChatService;
import com.messanger.WebMassanger.service.FriendsService;
import com.messanger.WebMassanger.service.UserService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {

    private final UserService userService;
    private final FriendsService friendsService;
    private final ChatService chatService;

    public ChatController(UserService userService, FriendsRepository friendsRepository, FriendsService friendsService, ChatService chatService) {
        this.userService = userService;
        this.friendsService = friendsService;
        this.chatService = chatService;
    }

    @GetMapping("/friend/{friend}")
    public String chatWithFriend(@PathVariable String friend, Model model,OAuth2AuthenticationToken authentication){
        String email = authentication.getPrincipal().getAttribute("email");
        String myName = userService.findNameByEmail(email);
        List<Chat> messages = chatService.getAllMessagesBetween(myName,friend);

        model.addAttribute("friendName", friend);
        model.addAttribute("myName", myName);
        model.addAttribute("messages", messages);


        return "chat";
    }
    @PostMapping("/friend/{friend}/message")
    public String message(@PathVariable String friend, OAuth2AuthenticationToken authentication, @RequestParam("message") String message)
    {
        String email = authentication.getPrincipal().getAttribute("email");
        String myName = userService.findNameByEmail(email);
        chatService.newMessage(myName,friend,message);



        return "redirect:/friend/{friend}";
    }




}
