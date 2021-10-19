package com.example.assignment.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Mail {
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();

}
