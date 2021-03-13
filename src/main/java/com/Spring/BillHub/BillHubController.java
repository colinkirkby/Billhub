package com.Spring.BillHub;

import com.Spring.BillHub.model.AccountType;
import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BillHubController {
    private static final String CLIENT_ID = "896111746806-t9h64076lkejcuv9qt5v99k7o534vt3c.apps.googleusercontent.com";

    @Autowired
    private UserRepo userRepo;

    /*
        See all users in the Database
     */
    @GetMapping("/homepage/all-users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    /*
        login with google, if new user, add to database
     */
    @GetMapping("/login-successful")
    ResponseEntity<Object> login(@AuthenticationPrincipal OidcUser user) {
        String email = (String) user.getAttributes().get("email");

        User googleSignInCheck = userRepo.findByEmail(email);

        if(googleSignInCheck == null) {
            User newUser = new User();
            newUser.setFirstName((String)user.getAttributes().get("given_name"));
            newUser.setLastName((String) user.getAttributes().get("family_name"));
            newUser.setEmail((String) user.getAttributes().get("email"));
            newUser.setAccountType(AccountType.GMAIL);
            userRepo.save(newUser);

            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(googleSignInCheck, HttpStatus.OK);
    }

    /*
        Authenticate Google sign in with Token sent from Google User
     */
    @RequestMapping(value = "/homepage/verify", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void handle(@RequestBody String idTokenString, HttpServletResponse response) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();


        GoogleIdToken idToken = googleIdTokenVerifier.verify(idTokenString);

        if(idToken != null) {
            Payload payload = idToken.getPayload();

            //optional identifier
            String userId = payload.getSubject();

            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            User newUser = userExits(email);

            //new User
            if(newUser == null) {
                newUser = new User();
                newUser.setEmail(email);
                newUser.setFirstName(givenName);
                newUser.setLastName(familyName);
                newUser.setAccountType(AccountType.GMAIL);
                newUser.toString();
            } else {
                System.out.println("Welcome back!");
            }

            response.setHeader("Location", "/homepage");

        } else {
            System.out.println("Invalid ID token");
        }
    }


    //************************* Helper functions below *************************\\
    public User userExits(String email) {
        return userRepo.findByEmail(email);
    }

}
