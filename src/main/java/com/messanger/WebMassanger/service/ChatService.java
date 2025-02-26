package com.messanger.WebMassanger.service;

import com.messanger.WebMassanger.model.Chat;
import com.messanger.WebMassanger.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final FriendsService friendsService;
    public ChatService(ChatRepository chatRepository, FriendsService friendsService) {
        this.chatRepository = chatRepository;
        this.friendsService = friendsService;
    }

    public Chat newMessage(String myName,String friendName,String message){
        Chat chat = new Chat(myName,friendName,message,myName);
         return chatRepository.save(chat);
    }

   public List<Chat> getAllMessagesBetween(String myName,String friendName){
        try {
            List<Chat> messagesFromFriendToMe = chatRepository.findAllByFriendNameAndMyNameOrderByTimestamp(friendName, myName);
            List<Chat> messagesFromMeToFriend = chatRepository.findAllByFriendNameAndMyNameOrderByTimestamp(myName, friendName);

            return Stream.concat(messagesFromFriendToMe.stream(), messagesFromMeToFriend.stream())
                    .sorted((c1, c2) -> c1.getTimestamp().compareTo(c2.getTimestamp()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Не вдалося отримати повідомлення чату: " + e.getMessage(), e);
        }
    }



}
