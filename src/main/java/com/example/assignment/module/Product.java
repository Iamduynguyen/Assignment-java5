package com.example.assignment.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;
    @NotNull
    private int price;
    private int discount;
    private int view;
    private int sold;
    @NotNull
    private int quantity;
    private String image;
    private String content;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Oderdetall> oderdetallList;
}
