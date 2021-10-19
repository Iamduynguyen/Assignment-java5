package com.example.assignment.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int status;
    private int amouttotal;
    private Date created;
    @ManyToOne
    @JoinColumn(name = "staff")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @OneToMany(mappedBy = "oder")
    private List<Oderdetall> lstOder;
    private String voucherID;

}
