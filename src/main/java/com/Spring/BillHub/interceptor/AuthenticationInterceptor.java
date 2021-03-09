package com.Spring.BillHub.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.Spring.BillHub.annotation.PassToken;
import com.Spring.BillHub.annotation.UserLoginToken;
import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;
import com.Spring.BillHub.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Autowired
    UserService userService;
	@Autowired
	UserRepo userRepo;
	
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {    //object is a controller
    	
    	//step 1: extract token from http request
    	String token = httpServletRequest.getHeader("token");             
        
        //step 2: skip if the controller is not mapping to method
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        
        //step 3: check for passtoken annotation. if there is a passtoken annotation, then skip through this process
        HandlerMethod handlerMethod=(HandlerMethod)object;     //casting object to type HandlerMethod
        Method method=handlerMethod.getMethod();               //get handling method
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //step 4: check for UserLoginToken annotation. if there is a UserLoginToken annotation, then extract the annotation and verify
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // if there is no token, throw "please login in"
                if (token == null) {
                    throw new RuntimeException("please login first");
                }
                // extract email from token
                String email;
                try {
                    email = JWT.decode(token).getAudience().get(0);
                } 
                catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                //find this user in userRepo
                User user = userRepo.findByEmail(email);
                //if cannot find this user
                if (user == null) {
                    throw new RuntimeException("user does not exist");
                }
                // verify token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    //provide empty postHandle and afterCompletion methods here because we are implementing HandlerInterceptor interface
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}
}
