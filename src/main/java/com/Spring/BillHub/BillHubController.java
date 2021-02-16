package com.Spring.BillHub;

import com.Spring.BillHub.model.User;
import com.Spring.BillHub.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class BillHubController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public void test() {
        User user = new User();
        user.setLastName("Name");
        user.setLastName("Last");
        user.setId(1);

        userRepository.save(user);
        // test if User saved in database
        System.out.println(userRepository.count());
    }

}
