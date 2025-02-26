package com.messanger.WebMassanger.controller;

import com.messanger.WebMassanger.repository.FriendsRepository;
import com.messanger.WebMassanger.service.FriendsService;
import com.messanger.WebMassanger.service.UserService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {


    private final UserService userService;
    private final FriendsRepository friendsRepository;
    private final FriendsService friendsService;

    public ProfileController(UserService userService, FriendsRepository friendsRepository, FriendsService friendsService) {
        this.userService = userService;
        this.friendsRepository = friendsRepository;
        this.friendsService = friendsService;
    }


    @GetMapping("/")
    public String home(){
        return "index";
    }


    @GetMapping("/profile")
    public String profilePage(OAuth2AuthenticationToken authentication, Model model) {
        String email = authentication.getPrincipal().getAttribute("email");
        String picture = authentication.getPrincipal().getAttribute("picture");


        // Додаємо дані в модель для передачі в шаблон
        model.addAttribute("userEmail", email);
        model.addAttribute("userPicture", picture);

        if(userService.IsEmailExists(email)){

            return "redirect:/messenger";
        }
        return "profileReg";
    }
    @PostMapping("/profile/add")
    public String addName(@RequestParam("name") String name, OAuth2AuthenticationToken authentication, Model model){
      String email = authentication.getPrincipal().getAttribute("email");

      if(!userService.isNameExists(name)) {
          userService.register(email, name);
          model.addAttribute("name", userService.findName(name));
          return "redirect:/messenger";
      }
      return "redirect:/profile";
    }
    @GetMapping("/messenger")
    public String messenger(Model model,OAuth2AuthenticationToken authentication){
        String email = authentication.getPrincipal().getAttribute("email");
        String Myname = userService.findNameByEmail(email);
        model.addAttribute("name",Myname);
        model.addAttribute("friends",friendsService.myFriends(Myname));
        return "messenger";
    }

}
