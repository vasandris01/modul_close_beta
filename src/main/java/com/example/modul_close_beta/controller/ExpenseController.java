package com.example.modul_close_beta.controller;

import com.example.modul_close_beta.model.Expense;
import com.example.modul_close_beta.model.Person;
import com.example.modul_close_beta.service.EntityService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
    private EntityService entityService;

    @GetMapping("/new/{person-id}")
    public String createExpense(Model model, @PathVariable("person-id") Integer personId){
        model.addAttribute("personId", personId);
        model.addAttribute("expense", new Expense());
        return "expense_form";
    }

    @PostMapping("/add/{person-id}")
    public String createExpense(@ModelAttribute("expense") Expense expense, @PathVariable("person-id") Integer personId){
        expense.setOwner(entityService.getPersonById(personId));
        entityService.saveExpense(expense);
        return "redirect:/home";
    }
}
