package com.example.assignment.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Oderdetall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private int amount;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "oder")
    private Oder oder;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
}
