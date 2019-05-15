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
        Entry entry1 = new Entry(body1, user);
        entry1 = entryRepo.save(entry1);
        watsonController.requestTones(entry1);
    }

    public void post2(UserAccount user) {
        String body2 = "I had a rough day today. I caught alot of flack at work for a mistake that wasn't even really mine. I did my due diligence and carried a complaint up the ladder, and now im getting punished for it.";
        Entry entry2 = new Entry(body2, user);
        entry2 = entryRepo.save(entry2);
        watsonController.requestTones(entry2);
    }

    public void post3(UserAccount user) {
        String body3 = "I think I am going to quit. I am obviously not appreciated here. I know its silly but i keep thinking that if I quit they'll realize how much they need me.";
        Entry entry3 = new Entry(body3, user);
        entry3 = entryRepo.save(entry3);
        watsonController.requestTones(entry3);
    }

    public void post4(UserAccount user) {
        String body4 = "I had like the most amazing day ever today. I met an Iron man impersonator, I ate lots of really good food, I met a cutie. Things are looking up.";
        Entry entry4 = new Entry(body4, user);
        entry4 = entryRepo.save(entry4);
        watsonController.requestTones(entry4);
    }

    public void post5(UserAccount user) {
        //        Last days of the suicide kid, by Charles Bukowski
        String body5 = "I can see myself now \n" +
                "after all these suicide days and nights, \n" +
                "being wheeled out of one of those sterile rest homes \n" +
                "(of course, this is only if I get famous and lucky) \n" +
                "by a subnormal and bored nurse \n";
        Entry entry5 = new Entry(body5, user);
        entry5 = entryRepo.save(entry5);
        watsonController.requestTones(entry5);
    }


}
