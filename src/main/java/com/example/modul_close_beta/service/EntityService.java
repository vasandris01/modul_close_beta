package com.example.modul_close_beta.service;

import com.example.modul_close_beta.model.Expense;
import com.example.modul_close_beta.model.Person;
import com.example.modul_close_beta.repo.ExpenseRepo;
import com.example.modul_close_beta.repo.PersonRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void savePerson(Person person) {
        personRepo.save(person);
    }

    public void saveExpense(Expense expense) {
        expenseRepo.save(expense);
    }

    @Transactional
    public void deletePersonById(Integer id) {
        personRepo.deleteById(id);
    }

    @Transactional
    public void deleteExpensesByPerson(Person person) {
        expenseRepo.deleteAllByOwner(person);
    }


    public Person getMostExpensesPerson() {
        Map<Person, Integer> personExpenses = fillPersonsMap();
        return getMaxExpensesFromPersonExpensesMap(personExpenses);
    }

    public Person getMaxExpensesFromPersonExpensesMap(Map<Person, Integer> personExpenses){
        int max = 0;
        for (var actual: personExpenses.entrySet()) {
            if(actual.getValue() > max){
                max = actual.getValue();
            }
        }
        for (var actual: personExpenses.entrySet()) {
            if(actual.getValue() == max){
                return actual.getKey();
            }
        }
        return null;
    }

    private Map<Person, Integer> fillPersonsMap() {
        Map<Person, Integer> personExpenses = new HashMap<>();
        fillPersonsMapWithPerson(personExpenses);
        fillPersonsMapWithExpenses(personExpenses);
        return personExpenses;
    }

    private void fillPersonsMapWithPerson(Map<Person, Integer> personExpenses) {
        List<Person> persons = getAllPerson();
        for (Person actual : persons) {
            personExpenses.put(actual, 0);
        }
    }

    private void fillPersonsMapWithExpenses(Map<Person, Integer> personExpenses) {
        List<Expense> expenses = getAllExpenses();
        for (Expense actual : expenses) {
            Person actualPerson = actual.getOwner();
            personExpenses.put(actualPerson, personExpenses.get(actualPerson) + actual.getAmount());
        }
    }
}
