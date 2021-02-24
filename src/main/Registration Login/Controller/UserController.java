package net.java.springboot.web;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import net.java.springboot.model.Role;
import net.java.springboot.model.User;
import net.java.springboot.repository.UserRepository;
import net.java.springboot.service.UserService;
import net.java.springboot.service.UserServicelmpl;
import net.java.springboot.web.dto.UserRegistrationDto;

@RestController         //use @controller annotation to let spring know that is class is a controller to handle html request
public class UserController {
     @Autowired
	private UserService userService;
     @Autowired
	private UserRepository repository;

	//constructor
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")          //annotate userRegistrationDto with "user"
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	//@GetMapping
	//public String showRegistrationForm() {
		//return "registration";       
	//}
	
	@PostMapping("/registration")      //tells spring that this method will handle post registration request
	public User registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		// try {
			// 
		// }
		return userService.save(registrationDto);
	}
	
		
	@PostMapping("/login")
    public User loadUserByUsername(@RequestBody String username) throws UsernameNotFoundException{
		User user = repository.findByEmail(username); 
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return repository.findByEmail(username);
		//return user.getFirstName();
	}
	
	// @PostMapping("/login")            //tells spring that this method will handle post login request
	
}  
