package com.Spring.BillHub.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.Spring.BillHub.dto.UserRegistrationDto;
import com.Spring.BillHub.model.User;


public interface UserService extends UserDetailsService{    //we pass a dto(data transfer object) object
    User save(UserRegistrationDto registrationDto);         //save user registration data to the database
}
