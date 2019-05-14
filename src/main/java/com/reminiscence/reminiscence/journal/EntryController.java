package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/entry/{id}")
    public String viewSingleEntry(
            @PathVariable long id,
            Model model,
            Principal p
    ) {
        UserAccount user = accountRepo.findByUsername(p.getName());
        Optional userId = accountRepo.findById(user.getId());
        Optional<Entry> foundEntry = entryRepo.findById(id);
        //Optional foundEntry

        if(userId != foundEntry.getUser)



        if (foundEntry.isPresent()) {
            Entry entry = foundEntry.get();
            model.addAttribute("entry", entry);
            return "singleEntry";
        }
        throw new EntryNotFoundException();
    }

    @GetMapping("/entry/{id}/update")
    public String getEntry(
            @PathVariable long id,
            Model model
    ) {
        Optional<Entry> foundEntry = entryRepo.findById(id);
        if (foundEntry.isPresent()) {
            Entry entry = foundEntry.get();
            model.addAttribute("entry", entry);
            return "updateEntry";
        }
        throw new EntryNotFoundException();
    }

    @PutMapping("/entry/{id}/update")
    public String updateEntry(
            @PathVariable long id,
            @RequestParam String body,
            Model model
    ) {
        Optional<Entry> foundEntry = entryRepo.findById(id);
        if (foundEntry.isPresent()) {
            Entry entry = foundEntry.get();
            entry.setBody(body);
            entry.setEdited(true);
            entryRepo.save(entry);
        } else {
            throw new EntryNotFoundException();
        }
        return "home";
    }

    @DeleteMapping("/entry/{id}/update")
    public String deleteEntry(
            @RequestParam long id
    ) {
        entryRepo.deleteById(id);
        return "home";
    }

}

