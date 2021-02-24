package com.Spring.BillHub;

import com.Spring.BillHub.model.AccountType;
import com.Spring.BillHub.model.User;
import com.Spring.BillHub.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class BillHubController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/all-users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }


    // login with google, if new user, add to database
    @GetMapping("/login-gmail-successful")
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

            return "Hello, " + newUser.getFirstName() + ", welcome to BillHub!";
        }

        return "Hello " + googleSignInCheck.getFirstName() + ", welcome to BillHub!";
    }

    @GetMapping("/login-gmail")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "login-gmail");
        return new ModelAndView("redirect:/login-gmail-successful", model);
    }
}
