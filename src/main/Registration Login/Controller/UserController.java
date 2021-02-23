package net.java.springboot.web;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.springboot.model.User;
import net.java.springboot.service.UserService;
import net.java.springboot.web.dto.UserRegistrationDto;

@RestController         //use @controller annotation to let spring know that is class is a controller to handle html request
@CrossOrigin(origins= {"http://localhost:3000"})
@RequestMapping     

public class UserController {
     
	private UserService userService;

	//constructor
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")                                 //save a new user in the user model
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	//@GetMapping
	//public String showRegistrationForm() {
		//return "registration";       
	//}
	
	@PostMapping("/registration")      //tells spring that this method will handle post registration request
	public User registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		try {
			
		}
		return userService.save(registrationDto);
		//return "redirect:/registration?success";   //redirct to the registration page with success message, success is a flag
	}
	
	
	@PostMapping("/login")            //tells spring that this method will handle post login request
	
}  
