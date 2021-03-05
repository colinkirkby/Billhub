package com.Spring.BillHub.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Spring.BillHub.dto.UserRegistrationDto;
import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;
import com.Spring.BillHub.service.UserService;

import ch.qos.logback.classic.Logger;


@CrossOrigin(origins = "http://localhost:3000")
@RestController        
@RequestMapping("/api/v1/")
public class UserController {
     @Autowired
	private UserService userService;
     @Autowired
	private UserRepo repository;

	//constructor
	public UserController(UserService userService) {
		super();
		this.userService = userService;
		this.repository = repository;
	}
	
	@ModelAttribute("user")          //annotate userRegistrationDto with "user"
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	

	@PostMapping("/registration")
	public User registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
		return userService.save(registrationDto);
	}
	
		
	@PostMapping("/login")
    public String userLogin(@RequestBody UserRegistrationDto registrationDto) throws UsernameNotFoundException{
		User user = repository.findByEmail(registrationDto.getEmail()); 
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		if (user.getPassword().equals(registrationDto.getPassword()) ) {
			return "Success";
		}
		else throw new UsernameNotFoundException("Invalid username or password");
	}
}
