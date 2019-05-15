package com.reminiscence.reminiscence.graph;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphController {

    @GetMapping("/pieGraph")
    public String pieGraph(Model model) {
        int anger = 6;
        int fear = 12;
        int sadness = 6;
        int analytical = 7;
        int confident = 3;
        int tentative = 6;
        
        model.addAttribute("anger", anger);
        model.addAttribute("fear", fear);
        model.addAttribute("sadness", sadness);
        model.addAttribute("analytical", analytical);
        model.addAttribute("confident", confident);
        model.addAttribute("tentative", tentative);
        return "pieGraph";
    }
}
