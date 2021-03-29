package com.Spring.BillHub.Controller;

// import java.util.Collection;
// import java.util.stream.Collectors;
// import javax.servlet.http.HttpServletRequest;

import com.Spring.BillHub.model.Transaction;

import javax.validation.Valid;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

	@PostMapping("/newtrans")
    public Object newTrans(@RequestBody String[] newTransaction) 
	{
		Transaction transaction = new Transaction(newTransaction);
		User user = repository.findByEmail(transaction.getEmail());

		Long id = (long) user.getTransactionsList().size() + 1;
		transaction.setId(id);
    
		user.addTransaction(transaction);
        repository.save(user);

		System.out.println("added transaction and saved user");
		return 200;
    }


	@GetMapping("/entries")
	@ResponseBody
	public Object[] entryList(@RequestParam String ID)
	{
		User user = repository.findByEmail(ID);
		int numberOfTransactions = user.getTransactionsList().size();
		
		if (numberOfTransactions == 0)
		{
			Object[] emptyList = new Object[1];
			emptyList[0] = 204;
			return emptyList;
		}
		
		Object[] transactionArray = new Object[numberOfTransactions];

		transactionArray = user.getTransactionsList().toArray();
		System.out.println("got the transactions");
		System.out.println("there are " + numberOfTransactions + " transactions");
		return transactionArray;
	}
	
	//This section is for testing only!
	@UserLoginToken
	@GetMapping("/dashboard")
	public String getMessage() {
		return "Welcome to BillHub!";
	}
}
