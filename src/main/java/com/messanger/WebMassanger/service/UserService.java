package com.messanger.WebMassanger.service;

import com.messanger.WebMassanger.model.User;
import com.messanger.WebMassanger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String findName(String name){
        User unique_name = userRepository.findByName(name);
        name = unique_name.getName();
        return name;
    }
    public String findNameByEmail(String email){
        User u = userRepository.findByEmail(email);
        return u != null ? u.getName() : "Невідомий нікнейм";
    }

    public String myname(OAuth2AuthenticationToken authentication){
        String email = authentication.getPrincipal().getAttribute("email");
        User user = new User();

        user = userRepository.findByEmail(email);

        String myname = user.getName();

        return myname;
    }
    public User register(String email, String name) {
        User unique_email = userRepository.findByEmail(email);
        User unique_name = userRepository.findByName(name);

        if (unique_email == null && unique_name == null) {
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public boolean IsUserexists(String email,String name){return userRepository.findByEmail(email) != null && userRepository.findByName(name) != null;}

    public boolean IsEmailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean isNameExists(String name) {
        return userRepository.findByName(name) != null;
    }

    public boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z]+$");
    }


}
