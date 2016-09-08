package com.github.handioq.shopapp.controller;

import com.github.handioq.shopapp.repository.UserRepository;
import com.github.handioq.shopapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String name) {
        String userId = "";
        try {
            User user = new User(email, name);
            userRepository.save(user);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User successfully created with id = " + userId;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userRepository.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User successfully deleted!";
    }

    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId = "";
        try {
            User user = userRepository.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            User user = userRepository.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User successfully updated!";
    }

}