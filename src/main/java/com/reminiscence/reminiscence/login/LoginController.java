package com.reminiscence.reminiscence.login;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    AccountRepo accountRepo;

    @GetMapping("login")
    public String getLogin(
            @RequestParam(required = false) boolean errored,
            Principal p,
            Model model
    ) {
        //Todo; refactor so that the try catch is unnecessary
        try {
            UserAccount user = accountRepo.findByUsername(p.getName());
            if (user != null) {
                model.addAttribute("errored", true);
                return "redirect:/home/";
            }
        } catch (Exception E) {

        }
        return "login";
    }

}
