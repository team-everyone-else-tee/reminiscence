package com.reminiscence.reminiscence.graph;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import com.reminiscence.reminiscence.journal.Entry;
import com.reminiscence.reminiscence.watson.Tone;
import com.reminiscence.reminiscence.watson.ToneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GraphController {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    ToneRepo toneRepo;

    @GetMapping("/pieGraph")
    public String pieGraph(
            Model model,
            Principal p
    ) {
        //Collect every entry associated with a user
        UserAccount user = accountRepo.findByUsername(p.getName());
        List<Entry> journal = user.getJournal();
        //Collect every tone associated with every entry of a user
        List<Tone> allTones = new ArrayList<>();
        for (Entry e : journal) {
            allTones.addAll(e.getTones());
        }

        //Initialize every possible tone
        int anger = 0;
        int fear = 0;
        int sadness = 0;
        int analytical = 0;
        int confident = 0;
        int tentative = 0;

        //Increment tone counters as they are encountered
        for (Tone tone : allTones) {
            switch (tone.getTone()) {
                case "Anger":
                    anger++;
                    break;
                case "Fear":
                    fear++;
                    break;
                case "Sadness":
                    sadness++;
                    break;
                case "Analytical":
                    analytical++;
                    break;
                case "Confident":
                    confident++;
                    break;
                case "Tentative":
                    tentative++;
                    break;
            }
        }
        // Attach every tone counter to a model and pass it to the page
        model.addAttribute("anger", anger);
        model.addAttribute("fear", fear);
        model.addAttribute("sadness", sadness);
        model.addAttribute("analytical", analytical);
        model.addAttribute("confident", confident);
        model.addAttribute("tentative", tentative);
        return "pieGraph";
    }
}
