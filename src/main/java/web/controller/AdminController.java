package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String helloAdmin(){
        return "admin";
    }



    @GetMapping(value = "/admin")
    public String listUsers(Model model){
        List<User> userList = userService.listUsers();
        model.addAttribute("listUsers", userList);
        return "admin";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") Long id ){
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }
    @PostMapping("/edit")
    public String editUser(User user){
        userService.updateUser(user);
        return "redirect:/admin";
    }

}
