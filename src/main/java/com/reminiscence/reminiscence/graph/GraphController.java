package com.reminiscence.reminiscence.graph;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphController {

    @GetMapping("/pieGraph")
    public String pieGraph(Model model) {
        model.addAttribute("pass", 50);
        model.addAttribute("fail", 50);
        return "pieGraph";
    }
}
