package com.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping(value = "/staticPage", method = RequestMethod.GET)
    public String RedirectStaticPage() {
        return "redirect:/pages/final.html";
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String Redirect() {
        return "redirect:finalPage";
    }

    @RequestMapping(value = "/finalPage", method = RequestMethod.GET)
    public String RedirectFinalPage() {
        return "final";
    }



}