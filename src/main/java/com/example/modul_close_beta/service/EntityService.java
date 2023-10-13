package com.example.modul_close_beta.service;

import com.example.modul_close_beta.model.Expense;
import com.example.modul_close_beta.model.Person;
import com.example.modul_close_beta.repo.ExpenseRepo;
import com.example.modul_close_beta.repo.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntityService {
    private PersonRepo personRepo;
    private ExpenseRepo expenseRepo;

    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public Person getPersonById(Integer id) {
        return personRepo.findById(id).orElse(null);
    }

    public List<Expense> getAllExpensesByPerson(Person person) {
        return expenseRepo.getExpensesByOwner(person);
    }
}
