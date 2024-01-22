package com.e_commerce.Controller;



import com.e_commerce.DTO.ResponseDto;
import com.e_commerce.DTO.SignInDto;
import com.e_commerce.DTO.SignInResponseDto;
import com.e_commerce.DTO.SignupDto;
import com.e_commerce.Entity.User;
import com.e_commerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return userService.signUp(signupDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

}
