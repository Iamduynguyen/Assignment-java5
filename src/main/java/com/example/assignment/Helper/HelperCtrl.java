package com.example.assignment.Helper;


import com.example.assignment.module.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.io.File;

@Service
public class HelperCtrl {
    @Autowired
    ServletContext application;
    @Autowired
    JavaMailSender sender;

    public boolean saveFile(MultipartFile file, String link) {
        try {
            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                File newFile = new File(link + "//" + filename);
                file.transferTo(newFile);
                System.out.println(filename);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public boolean sendMailTo(Mail mail) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(mail.getFromEmail());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody());
            helper.setTo(mail.getToEmail());
            helper.setReplyTo(mail.getFromEmail());
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
