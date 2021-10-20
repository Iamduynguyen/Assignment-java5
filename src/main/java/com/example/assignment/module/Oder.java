package com.example.assignment.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int status;
    private Date created;
    private int amouttotal;
    @ManyToOne
    @JoinColumn(name = "staff")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @OneToMany(mappedBy = "oder")
    private List<Oderdetall> lstOder;
    private String voucherID;


    public int getAmouttotals(List<Oderdetall> lstOder) {
        int total = 0;
        for (Oderdetall x: lstOder){
            total += x.getAmount(x.getProduct());
        }
        return total;
    }
}
