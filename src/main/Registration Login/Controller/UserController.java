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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import net.java.springboot.model.Role;
import net.java.springboot.model.User;
import net.java.springboot.repository.UserRepository;
import net.java.springboot.service.UserService;
import net.java.springboot.web.dto.UserRegistrationDto;

@RestController         //use @controller annotation to let spring know that is class is a controller to handle html request
@RequestMapping    //send html request to this address
public class UserController {
     @Autowired
	private UserService userService;
	private UserRepository userRepository;

	//constructor
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")          //annotate userRegistrationDto with "user"
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@RequestMapping(path="/registration", method=RequestMethod.POST)       //tells spring that this method will handle post registration request
	public User registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		return userService.save(registrationDto);
		//return "redirect:/registration?success";   //redirct to the registration page with success message, success is a flag
	}
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user =userRepository.findByEmail(username);
		if(user ==null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
