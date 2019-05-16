package com.reminiscence.reminiscence.Demonstration;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import com.reminiscence.reminiscence.journal.Entry;
import com.reminiscence.reminiscence.journal.EntryRepo;
import com.reminiscence.reminiscence.watson.WatsonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DemonstrationController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    WatsonController watsonController;

    @GetMapping("/Demonstration")
    public void populateDB(Principal p) {
        UserAccount user = accountRepo.findByUsername(p.getName());
        post1(user);
        post2(user);
        post3(user);
        post4(user);
        post5(user);
    }

    public UserAccount createUser1() {
        UserAccount user = new UserAccount("Fartbroker", "Password", this.encoder, "2019-01-01");
        user = accountRepo.save(user);
        return user;

    }

    public void post1(UserAccount user) {
        String body1 = "I had a really interesting conversation with Becky today, and they recommended this application. I am really excited to start practicing mindfulness in my langague";
        Entry entry1 = new Entry(body1, "2019-1-1", user);
        entry1 = entryRepo.save(entry1);
        watsonController.requestTones(entry1);
    }

    public void post2(UserAccount user) {
        String body2 = "I had a rough day today. I caught alot of flack at work for a mistake that wasn't even really mine. I did my due diligence and carried a complaint up the ladder, and now im getting punished for it.";
        Entry entry2 = new Entry(body2, "2019-2-13", user);
        entry2 = entryRepo.save(entry2);
        watsonController.requestTones(entry2);
    }

    public void post3(UserAccount user) {
        String body3 = "I am really starting to believe that my work place has a toxic culture and mindset. Im honestly afraid to go back, my peers make it very obvious that they dont care for my presence . ";
        Entry entry3 = new Entry(body3, "2019-2-26", user);
        entry3 = entryRepo.save(entry3);
        watsonController.requestTones(entry3);
    }

    public void post4(UserAccount user) {
        String body4 = "I had like the most amazing day ever today. I met an Iron man impersonator, I ate lots of really good food, I met a cutie. I didnt know this about myself, but i learned today that I can eat like three ben and jerries all by myself! Amazing. Things are looking up.";
        Entry entry4 = new Entry(body4, "2019-3-4", user);
        entry4 = entryRepo.save(entry4);
        watsonController.requestTones(entry4);
    }

    public void post5(UserAccount user) {
        //        Invictus, by William Earnest Henley
        String body5 = "Out of the night that covers me," +
                "Black as the Pit from pole to pole," +
                "I thank whatever gods may be" +
                "For my unconquerable soul." +
                "In the fell clutch of circumstance" +
                "I have not winced nor cried aloud." +
                "Under the bludgeonings of chance" +
                "My head is bloody, but unbowed." +
                "Beyond this place of wrath and tears" +
                "Looms but the Horror of the shade" +
                "And yet the menace of the years" +
                "Finds, and shall find, me unafraid." +
                "It matters not how strait the gate," +
                "How charged with punishments the scroll." +
                "I am the master of my fate:" +
                "I am the captain of my soul.";
        Entry entry5 = new Entry(body5, "2019-5-16", user);
        entry5 = entryRepo.save(entry5);
        watsonController.requestTones(entry5);
    }


}
