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

    @GetMapping("/person/{id}")
    public String getPersonById(Model model, @PathVariable Integer id){
        Person person = entityService.getPersonById(id);
        model.addAttribute("expenses", entityService.getAllExpensesByPerson(person));
        model.addAttribute("person", person);
        return "person";
    }
}
