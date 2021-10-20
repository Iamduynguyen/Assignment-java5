package com.example.assignment.DAO;

import com.example.assignment.module.Oder;
import com.example.assignment.module.Oderdetall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OderdetallDao extends JpaRepository<Oderdetall,Integer> {

    @Query(value = "select o from  Oderdetall o where o.oder = :oder")
    public List<Oderdetall> getOderdetallsByOder(@Param("oder")Oder oder);
}
