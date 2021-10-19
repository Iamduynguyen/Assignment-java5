package com.example.assignment.DAO;


import com.example.assignment.module.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
    @Override
    Optional<Category> findById(Integer integer);
}
