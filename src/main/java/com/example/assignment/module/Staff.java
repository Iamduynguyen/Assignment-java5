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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Staff {
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
    private String image;
    private Boolean role;
    private Boolean active;
    private String phone;
    @OneToMany(mappedBy = "staff")
    private List<Oder> oderList;
}
