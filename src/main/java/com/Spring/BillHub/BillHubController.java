package com.Spring.BillHub;

import com.Spring.BillHub.model.User;
import com.Spring.BillHub.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BillHubController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/index")
    public void test() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Name");
        user.setLastName("Last");
        userRepository.save(user);

        System.out.println(userRepository.count());

        for(User u : userRepository.findAll()) {
            System.out.println(u.getFirstName());
        }
    }
}
