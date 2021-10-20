package com.example.assignment.DAO;

import com.example.assignment.module.Customer;
import com.example.assignment.module.Oder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Oderdao extends JpaRepository<Oder,Integer> {

    @Query(value = "SELECT o from Oder o where o.status = 0 and o.customer = :customer")
    public Oder getOderByCustomer(@Param("customer")Customer customer);
}
