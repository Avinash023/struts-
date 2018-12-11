package org.superbiz.struts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/addUser")
    public String addUserForm() {
        return "addUserForm";
    }

    @PostMapping("/addUser")
    public String addedUser(@RequestParam long id,@RequestParam String firstName,@RequestParam String lastName) {
        userRepository.save(new User(id,firstName,lastName));
        return "addedUser";
    }


    @GetMapping("/findUser")
    public String findUserForm() {
        return "findUserForm";
    }

    @PostMapping("/findUser")
    public String findUser(@RequestParam long id, Model model) {
        User user = userRepository.findOne(id);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "findUserForm";
        }

        model.addAttribute("user", user);
        return "displayUser";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "displayUsers";
    }
}
