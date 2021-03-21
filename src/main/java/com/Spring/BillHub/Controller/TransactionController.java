package com.Spring.BillHub.Controller;

import com.Spring.BillHub.model.Transaction;
import com.Spring.BillHub.model.User;

import org.json.*;
import com.Spring.BillHub.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Spring.BillHub.service.UserService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/")
public class TransactionController {
    @Autowired
    private UserRepo userRepo;
    
    //constructor
	public TransactionController(UserService userService) {
		super();
		this.userRepo = userRepo;
	}

/*
    Action: PUT
    Return: ResponseEntity
    Throws: RESTNotFound

    Front end sends a user Transaction in JSON, decode String using JSONObject, create new Transaction, find user
    by email and add to their list, might change to HashSet for time complexity
 */
    // @PutMapping(value = "/newtrans", consumes = "application/json")
    // public ResponseEntity<Object> newTrans(@RequestBody String transactionJSON) {
        // 
        // DEBUG CODE
// 
            // System.out.println("Made it in the function!");
// 
        
// 
// 
// 
        // JSONObject jsonObject = new JSONObject(transactionJSON);
// 
        // String email = jsonObject.getString("email");
        // String name = jsonObject.getString("name");
        // String type = jsonObject.getString("type");
        // String date = jsonObject.getString("date");
        // String amount = jsonObject.getString("amount");
// 
// 
        // User user = userRepo.findByEmail(email);
        // Long id = (long) user.getTransactionsList().size() + 1;
// 
        // Transaction transaction = new Transaction(email, name, type, date, amount, (long) 0);
// 
        // if(user == null) {
            // throw new RESTNotFound("User with email: " + email + "not found");
        // }
        // user.addTransaction(transaction);
// 
        // userRepo.save(user);
        // return new ResponseEntity<>(user, HttpStatus.OK);
    // }

    //****************** Custom errors ******************\\
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class RESTNotFound extends  RuntimeException{
        public RESTNotFound(){
        }
        public RESTNotFound(String message){
            super(message);
        }

    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public class RESTFailureToAdd extends RuntimeException{
        public RESTFailureToAdd(){
        }

        public RESTFailureToAdd(String message){
            super(message);
        }
    }
}
