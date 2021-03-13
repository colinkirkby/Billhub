package com.Spring.BillHub.Controller;

// import java.util.Collection;
// import java.util.stream.Collectors;
// import javax.servlet.http.HttpServletRequest;

import com.Spring.BillHub.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.servlet.ModelAndView;

import com.Spring.BillHub.annotation.UserLoginToken;
import com.Spring.BillHub.dto.UserRegistrationDto;
import com.Spring.BillHub.dto.UserTransactionDto;
import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;
import com.Spring.BillHub.service.TokenService;
import com.Spring.BillHub.service.UserService;
import com.alibaba.fastjson.JSONObject;
// import ch.qos.logback.classic.Logger;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import com.Spring.BillHub.service.UserServicelmpl;
import com.google.common.net.MediaType;

@CrossOrigin(origins = "http://localhost:3000")
@RestController        
@RequestMapping("/api/v1/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo repository;
	@Autowired
    private TokenService tokenService;
	private Object put;

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
    public Object userLogin(@RequestBody UserRegistrationDto registrationDto) throws UsernameNotFoundException{
		JSONObject jsonObject = new JSONObject();
		User user = repository.findByEmail(registrationDto.getEmail());
		if (user == null) {
			jsonObject.put("message", "login-failed, invalid username");
			return jsonObject;
		}
		else {
			if (user.getPassword().equals(registrationDto.getPassword()) ) {
				String token = tokenService.getToken(user);
				jsonObject.put("token", token);
				jsonObject.put("user", user);
				jsonObject.put("message", "Success");
				return jsonObject;
			}
			else {
				jsonObject.put("message", "Invalid username or password");
				return jsonObject;
			}
		}
		
	}
	//@RequestBody UserTransactionDto userTransactionDto
	// add a new transaction to the account
	@PostMapping("/newtrans")
	public Object newTrans(@RequestBody String[] newTransaction ) throws UsernameNotFoundException {
		Transaction transaction = new Transaction(newTransaction);
		System.out.println("adding new transaction");
		repository.findByEmail(transaction.getEmail()).addTransaction(transaction);

		return 200;

		//System.out.println(user.toString());
		//System.out.println(newTransaction[0]);
		//repository.save(user);


	}
	@GetMapping("/account")
	@ResponseBody
	public Object accountDetails(@RequestParam String ID)
	{
		User user = repository.findByEmail(ID);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstName", user.getFirstName());
		jsonObject.put("lastName", user.getLastName());

		return jsonObject;
	}


	//This section is for testing only!
	@UserLoginToken
	@GetMapping("/dashboard")
	public String getMessage() {
		return "Welcome to BillHub!";
	}
}
