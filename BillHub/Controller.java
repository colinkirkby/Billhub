package com.SpringBoot.BillHub.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// HTML files must go into the template folder under resources
@RestController
public class Controller {

    @GetMapping("/greeting")
    public String HelloWorld() {
        return "Hello World";
    }
}
