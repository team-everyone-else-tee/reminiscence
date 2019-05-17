package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import com.reminiscence.reminiscence.watson.Tone;
import com.reminiscence.reminiscence.watson.ToneRepo;
import com.reminiscence.reminiscence.watson.WatsonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class EntryController {

    @Autowired
    WatsonController watsonController;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    ToneRepo toneRepo;

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
    public RedirectView createEntry(
            @RequestParam String body,
            Principal p
    ) {
        UserAccount user = accountRepo.findByUsername(p.getName());
        Entry entry = new Entry(body);
        entry.setUser(user);
        entryRepo.save(entry);

        watsonController.requestTones(entry);

        return new RedirectView("/home");
    }

    @GetMapping("/entry/{id}")
    public String viewSingleEntry(
            @PathVariable long id,
            Model model,
            Principal p
    ) {

        Optional<Entry> foundEntry = entryRepo.findById(id);
        if (foundEntry.isPresent()) {
            UserAccount user = accountRepo.findByUsername(p.getName());
            Entry entry = foundEntry.get();
            if (user.getId() == entry.getUser().getId()) {
                model.addAttribute("entry", entry);
                if (entry.getTones() != null) {
                    model.addAttribute("tones", entry.getTones());
                }
                return "singleEntry";
            } else {
                throw new UnauthorizedAccountException();
            }
        }
        throw new EntryNotFoundException();
    }

    @GetMapping("/entry/{id}/update")
    public String getEntry(
            @PathVariable long id,
            Model model,
            Principal p
    ) {
        Optional<Entry> foundEntry = entryRepo.findById(id);
        if (foundEntry.isPresent()) {
            UserAccount user = accountRepo.findByUsername(p.getName());
            Entry entry = foundEntry.get();
            if (user.getId() == entry.getUser().getId()) {
                model.addAttribute("entry", entry);
                return "updateEntry";
            } else {
                throw new UnauthorizedAccountException();
            }
        }
        throw new EntryNotFoundException();
    }

    @PutMapping("/entry/{id}/update")
    public RedirectView updateEntry(
            @PathVariable long id,
            @RequestParam String body,
            Model model,
            Principal p
    ) {
        Optional<Entry> foundEntry = entryRepo.findById(id);
        if (foundEntry.isPresent()) {
            UserAccount user = accountRepo.findByUsername(p.getName());
            Entry entry = foundEntry.get();
            if (user.getId() == entry.getUser().getId()) {


                List<Tone> tones = entry.getTones();
                List<Tone> newTonelist = new ArrayList<>();
                entry.setTones(newTonelist);

                for (Tone tone : tones) {
                    toneRepo.deleteById(tone.getId());
                }


                entry.setBody(body);
                entry.setEdited(true);
                entryRepo.save(entry);
                watsonController.requestTones(entry);

                return new RedirectView("/home");


            } else {
                throw new UnauthorizedAccountException();
            }
        }
        throw new EntryNotFoundException();
    }

    @DeleteMapping("/entry/{id}/update")
    public RedirectView deleteEntry(
            @RequestParam long id,
            Principal p
    ) {
        Optional<Entry> foundEntry = entryRepo.findById(id);
        if (foundEntry.isPresent()) {
            UserAccount user = accountRepo.findByUsername(p.getName());
            Entry entry = foundEntry.get();
            if (user.getId() == entry.getUser().getId()) {

                List<Tone> tones = entry.getTones();
                for (Tone t : tones) {
                    toneRepo.deleteById(t.getId());
                }

                entryRepo.deleteById(entry.getId());
                return new RedirectView("/home");

            } else {
                throw new UnauthorizedAccountException();
            }
        } else {
            throw new EntryNotFoundException();
        }
    }
}

