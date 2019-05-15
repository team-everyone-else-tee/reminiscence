package com.reminiscence.reminiscence.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AccountController {

    @Autowired
    AccountRepo repo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String password
    ) throws DataIntegrityViolationException {
        UserAccount zapBrannigan = new UserAccount();
        zapBrannigan.setUsername(username);
        zapBrannigan.setPassword(password, this.encoder);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        zapBrannigan.setJoinDate(date);

        repo.save(zapBrannigan);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                zapBrannigan,
                null,
                zapBrannigan.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        return "redirect:/home";
    }

}
