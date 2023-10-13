package com.example.modul_close_beta.controller;

import com.example.modul_close_beta.model.Expense;
import com.example.modul_close_beta.model.Person;
import com.example.modul_close_beta.service.EntityService;
import lombok.AllArgsConstructor;
import org.hibernate.property.access.spi.EnhancedSetterImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class Rest {
    private EntityService entityService;

    @GetMapping("/persons")
    public List<Person> getAllPerson(){
        return entityService.getAllPerson();
    }
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        return entityService.getAllExpenses();
    }
    @GetMapping("/person/{id}/expenses")
    public List<Expense> getAllExpensesByPerson(@PathVariable("id") Integer id){
        return entityService.getAllExpensesByPerson(entityService.getPersonById(id));
    }
}
