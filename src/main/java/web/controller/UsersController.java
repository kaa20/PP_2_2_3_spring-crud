package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

}
