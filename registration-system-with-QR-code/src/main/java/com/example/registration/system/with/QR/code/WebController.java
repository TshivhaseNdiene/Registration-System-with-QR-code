package com.example.registration.system.with.QR.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            User registeredUser = userService.registerUser(user);
            model.addAttribute("user", registeredUser);
            return "redirect:/web/user/" + registeredUser.getId() + "/qr-code";
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred while registering user.");
            return "register";
        }
    }

    @GetMapping("/user/{id}/qr-code")
    public String showUserQRCode(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null && user.getQrCode() != null) {
            model.addAttribute("user", user);
            return "qr-code";
        } else {
            model.addAttribute("error", "User not found or QR code not available.");
            return "error";
        }
    }
}
