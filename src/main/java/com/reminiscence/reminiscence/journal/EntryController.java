package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class EntryController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    EntryRepo entryRepo;

    @GetMapping()
    public String getHome(
            Principal p,
            Model model) {

        UserAccount user = this.accountRepo.findByUsername(p.getName());
        List journal = user.getJournal();

        model.addAttribute("user", user);
        model.addAttribute("journal", journal);
        return "home";
    }

    @PostMapping("/entry")
    public String createEntry(
            @RequestParam String body,
            Principal p
    ) {
        UserAccount user = accountRepo.findByUsername(p.getName());
        Entry entry = new Entry(body);
        entry.setUser(user);
        entryRepo.save(entry);
        return "home";
    }
}

