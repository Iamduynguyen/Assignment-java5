package com.example.assignment.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String address;
    private String phone;
    private int rank;
    private String image;
    private Boolean sex;
    @OneToMany(mappedBy = "customer")
    private List<Oder> oders;
}
