package net.java.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
     @GetMapping("/login")
	 public String login() {
		 return "login";      //This method returns a login page
	 }
     
     //handle home page here
     @GetMapping("/")
     public String home() {
    	 return "index";           //This should be our home page
     }
}
