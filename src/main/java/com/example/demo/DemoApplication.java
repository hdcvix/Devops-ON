package com.example.demo;

import java.util.Date;

import com.example.demo.configuration.AppUser;
import com.example.demo.configuration.LoggedInUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String healthCheck() {
        return "HEALTH CHECK OK!";
    }

    @GetMapping("/secured")
    public Object secured(@LoggedInUser AppUser appUser) {
        return appUser.getUser();
    }

    @GetMapping("/secured-admin")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String securedAdmin() {
        return "Only admin can see this";
    }

    @GetMapping("/public")
    public String pub() {
        return "This is public endpoint";
    }

    @GetMapping("/what-is-the-time")
    public String time() {
        return new Date().toString();
    }

    @GetMapping("/devops")
    public String turma() {
        return "Zumbi";
    }

    @GetMapping("/image")
    @ResponseBody
    public String getImage() {
        return "<img src='https://learn.microsoft.com/pt-br/shows/devops-lab/media/devopslab-titlecard.png'>";
    }

    @GetMapping("/gif")
    @ResponseBody
    public String getGif() {
        return "<img src='https://media.giphy.com/media/3oEjI5VtIhHvK37WYo/giphy.gif' alt='Animated GIF'>";
    }
}
