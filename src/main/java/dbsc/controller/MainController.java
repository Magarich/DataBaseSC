package dbsc.controller;

import dbsc.entity.UserEntity;
import dbsc.entity.enums.Role;
import dbsc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserEntity user, Map<String, Object> model){
        UserEntity userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.put("message","User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }


}

