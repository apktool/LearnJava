package com.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView User() {
        return new ModelAndView("user", "command", new User());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String AddUser(@ModelAttribute("SpringWeb") User user, ModelMap model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("receivePaper", user.getReceivePaper());
        model.addAttribute("favoriteFrameworks", user.getFavoriteFrameworks());
        model.addAttribute("gender", user.getGender());
        model.addAttribute("favoriteNumber", user.getFavoriteNumber());

        return "userlist";
    }

    @ModelAttribute("webFrameworkList")
    public List<String> getWebFrameworkList() {
        List<String> webFrameworkList = new ArrayList<String>();

        webFrameworkList.add("String MVC");
        webFrameworkList.add("Spring Boot");
        webFrameworkList.add("Spring Integration");

        return webFrameworkList;
    }

    @ModelAttribute("numbersList")
    public List<String> getNumbersList() {
        List<String> numbersList = new ArrayList<String>();

        numbersList.add("1");
        numbersList.add("2");
        numbersList.add("3");
        numbersList.add("4");

        return numbersList;
    }
}
