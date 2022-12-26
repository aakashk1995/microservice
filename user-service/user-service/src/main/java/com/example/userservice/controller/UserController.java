package com.example.userservice.controller;

import com.example.userservice.dto.ResponseTemplateVO;
import com.example.userservice.dto.UserDepartmentDTO;
import com.example.userservice.entity.User;

import com.example.userservice.service.UserService;
import com.example.userservice.utils.GetCurrentHttpRequest;
import com.example.userservice.utils.GetToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        LOGGER.info("Saving user data ", user);
        return userService.saveUser(user);
    }


    @GetMapping("/users")
    public List<UserDepartmentDTO> getAllUserDetailsWithUserAndDepartment(){
        LOGGER.info("Fetching All users data");
        return userService.getAllUserDetailsWithUserAndDepartment();
    }

    @GetMapping("/users/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userid)  {
        LOGGER.info("Fetching details of user " + userid) ;



         return userService.getUserWithDepartment(userid);

    }

}
