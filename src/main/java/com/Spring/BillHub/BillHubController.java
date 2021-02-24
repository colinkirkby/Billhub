package com.Spring.BillHub;

import com.Spring.BillHub.model.AccountType;
import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;



@RestController
public class BillHubController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    // login with google, if new user, add to database
    @GetMapping("/user")
    String login(@AuthenticationPrincipal OidcUser user) {
        String email = (String) user.getAttributes().get("email");

        User googleSignInCheck = userRepo.findByEmail(email);

        if(googleSignInCheck == null) {
            User newUser = new User();
            newUser.setFirstName((String)user.getAttributes().get("given_name"));
            newUser.setLastName((String) user.getAttributes().get("family_name"));
            newUser.setEmail((String) user.getAttributes().get("email"));
            newUser.setAccountType(AccountType.GMAIL);
            userRepo.save(newUser);

            return newUser.toString();
        }

        return googleSignInCheck.toString();
    }
}
