package com.remittancemiddleware.remittancemiddleware.controller;

import com.remittancemiddleware.remittancemiddleware.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface UserController {
    User getUserById(@PathVariable int userId);
    User createUser(@RequestBody Map<String,String> creationDetails);
    User login(@RequestBody Map<String,String> loginDetails);
}
