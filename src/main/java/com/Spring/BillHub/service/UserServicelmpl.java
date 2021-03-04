package com.Spring.BillHub.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Spring.BillHub.dto.UserRegistrationDto;
import com.Spring.BillHub.model.Role;
import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;

//import net.java.springboot.model.Role;
//import net.java.springboot.model.User;
//import net.java.springboot.repository.UserRepository;
//import net.java.springboot.web.dto.UserRegistrationDto;


@Service
public class UserServicelmpl implements UserService{
    @Autowired
	private UserRepo userRepository;
	
	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	//constructor
	public UserServicelmpl(UserRepo userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) { //create a new user object
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), 
				registrationDto.getEmail(), 
//				passwordEncoder.encode(registrationDto.getPassword()),
				registrationDto.getPassword(),
				Arrays.asList(new Role("ROLE_USER")));
	    return userRepository.save(user);    //save the newly created object to the database
	}
	
	@Override
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
