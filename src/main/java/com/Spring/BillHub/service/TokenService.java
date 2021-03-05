package com.Spring.BillHub.service;

import org.springframework.stereotype.Service;
import com.Spring.BillHub.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service("TokenService")
public class TokenService {
	//The way that a token is created
    //private static final String JWTSECRET = "myScrect";
    public String getToken(User user) {
        // save email in token, use password as secret key
        String token= JWT.create().withAudience(user.getEmail()).sign(Algorithm.HMAC256(user.getPassword()));       
        return token;
    }
}
