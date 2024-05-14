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
        public String healthCheck(){
                return "HEALTH CHECK OK!";
        }

	@GetMapping("/secured")
	public Object secured(@LoggedInUser AppUser appUser){
		return appUser.getUser();
	}

	@GetMapping("/secured-admin")
	@PreAuthorize("hasRole('ROLE_admin')")
	public String securedAdmin(){
		return "Only admin can see  this";
	}
	
	@GetMapping("/public")
	public String pub(){
		
		return "This is public endpoint";
	}

	@GetMapping("/what-is-the-time")
	String time(){
		return new Date().toString();
	}
	
	@GetMapping("/devops")
	String turma(){
		return "Zumbi";
	}


    	@GetMapping("/image")
   	@ResponseBody
    	public String getImage() {
        // Aqui você pode retornar o caminho para a imagem ou o conteúdo da imagem
        // por meio de um objeto de byte ou base64.
        // Por exemplo:
        // return "<img src='/caminho/para/sua/imagem.jpg'>";
        return "<img src='https://learn.microsoft.com/pt-br/shows/devops-lab/media/devopslab-titlecard.png'>";
    }

}	
