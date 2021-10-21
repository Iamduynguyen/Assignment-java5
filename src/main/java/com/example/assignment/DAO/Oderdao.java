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

    @Query(value = "SELECT o from Oder o where o.status > 0 and o.customer = :customer")
    public List<Oder> getBought(@Param("customer")Customer customer);

    @Query(value = "SELECT o from Oder o where o.status = :status")
    public List<Oder> getWaiting(@Param("status")int status);

    @Query(value = "SELECT count(o.status) from Oder o where o.status = :status")
    public int getWaitings(@Param("status")int status);

}
