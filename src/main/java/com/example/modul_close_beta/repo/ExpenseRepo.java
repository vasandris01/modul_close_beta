package com.example.modul_close_beta.repo;

import com.example.modul_close_beta.model.Expense;
import com.example.modul_close_beta.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
    List<Expense> getExpensesByOwner(Person person);
}
