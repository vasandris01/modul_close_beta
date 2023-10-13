package com.example.modul_close_beta.controller;

import com.example.modul_close_beta.model.Person;
import com.example.modul_close_beta.service.EntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class PageController {
    private EntityService entityService;

    @GetMapping({"","/","/home"})
    public String getHome(Model model){
        model.addAttribute("persons", entityService.getAllPerson());
        return "home";
    }


}
