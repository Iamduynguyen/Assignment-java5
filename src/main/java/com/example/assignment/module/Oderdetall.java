package com.example.assignment.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class Oderdetall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "oder")
    private Oder oder;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    public int getAmount(){
        return (product.getPrice()-product.getDiscount())*quantity;
    }
}
