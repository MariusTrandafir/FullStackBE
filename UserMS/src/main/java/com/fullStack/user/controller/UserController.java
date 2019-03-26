package com.fullStack.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.fullStack.user.config.JwtTokenUtil;
import com.fullStack.user.model.ApiResponse;
import com.fullStack.user.model.AuthToken;
import com.fullStack.user.model.LoginUser;
import com.fullStack.user.model.User;
import com.fullStack.user.model.UserDto;
import com.fullStack.user.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ApiResponse<AuthToken> auth(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername()));
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ApiResponse<User> signup(@RequestBody UserDto user){
    	return new ApiResponse<>(HttpStatus.OK.value(),"User sign up succesfull.",userService.save(user));
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }
    
}
