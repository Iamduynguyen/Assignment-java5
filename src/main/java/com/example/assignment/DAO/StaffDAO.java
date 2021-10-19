package com.example.assignment.DAO;


import com.example.assignment.module.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDAO extends JpaRepository<Staff, Integer> {
}
