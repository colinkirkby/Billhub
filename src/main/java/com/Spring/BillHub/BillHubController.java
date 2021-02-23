package com.Spring.BillHub;

import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@RestController
public class BillHubController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @GetMapping("/loggedin")
    public String googleSignInCheck() {
        return "LOGGED IN WITH GMAIL";
    }
}
