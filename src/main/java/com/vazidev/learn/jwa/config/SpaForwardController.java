package com.vazidev.learn.jwa.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaForwardController {

    // Forward all routes (that don't contain a '.') to index.html so that
    // Angular can handle client-side routing when serving the built UI.
    @RequestMapping(value = { "/{path:[^\\.]*}", "/**/{path:[^\\.]*}" })
    public String forward() {
        return "forward:/index.html";
    }
}

