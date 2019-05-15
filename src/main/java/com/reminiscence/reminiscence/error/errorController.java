package com.reminiscence.reminiscence.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController {

    @GetMapping("/error")
    public String getError() {
        return "error";
    }
}
