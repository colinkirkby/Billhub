package net.java.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.java.springboot.service.UserService;
import net.java.springboot.web.dto.UserRegistrationDto;

@Controller         //use @controller annotation to let spring know that is class is a controller to handle html request
@RequestMapping("/registration")     //send html request to this address
public class UserRegistrationController {
     
	private UserService userService;

	//constructor
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")          //annotate userRegistrationDto with "user"
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";        //This must be same as the html name
	}
	
	@PostMapping      //tells spring that this method will handle post registration request
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";   //redirct to the registration page with success message, success is a flag
	}
}
