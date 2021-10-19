package com.example.assignment.DAO;


import com.example.assignment.module.Category;
import com.example.assignment.module.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Query(value = "select p from Product p where p.name like %:name%")
    List<Product> findByName(@Param("name") String name);

    @Query(value = "select p from Product p where p.category = :category")
    List<Product> findByCategory(@Param("category") Category category);

    @Query(value = "select p from Product p where p.category.id = :category")
    List<Product> findByCategoryandLimit(@Param("category") int category, Pageable pageable);

    @Query(value = "select p from Product p")
    List<Product> findLimit(Pageable pageable);
}
