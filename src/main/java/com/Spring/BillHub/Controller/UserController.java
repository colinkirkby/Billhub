package com.Spring.BillHub.Controller;

// import java.util.Collection;
// import java.util.stream.Collectors;
// import javax.servlet.http.HttpServletRequest;

import com.Spring.BillHub.model.Transaction;

import javax.validation.Valid;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
	public User registerUserAccount(@Valid @RequestBody UserRegistrationDto registrationDto) {
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
	
	//This section is for testing only!
	@UserLoginToken
	@GetMapping("/dashboard")
	public String getMessage() {
		return "Welcome to BillHub!";
	}
}
