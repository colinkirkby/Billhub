package com.Spring.BillHub;

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

    }
}
