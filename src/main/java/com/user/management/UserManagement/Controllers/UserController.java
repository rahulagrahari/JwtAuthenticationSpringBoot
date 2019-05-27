package com.user.management.UserManagement.Controllers;

import com.user.management.UserManagement.Modals.JwtToken;
import com.user.management.UserManagement.Modals.Role;
import com.user.management.UserManagement.Modals.ApplicationUser;
import com.user.management.UserManagement.Payload.AuthenticationResponse;
import com.user.management.UserManagement.Repositories.JwtTokenRepo;
import com.user.management.UserManagement.Repositories.RoleRepository;
import com.user.management.UserManagement.Services.UserServiceImpl;
import com.user.management.UserManagement.Utility.JwtTokenHandler;
import com.user.management.UserManagement.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    JwtTokenHandler jwtTokenHandler;


    @Autowired
    private JwtTokenRepo jwtTokenRepo;

    @Autowired
    RoleRepository roleRepository;


    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> registration(@Valid @RequestBody ApplicationUser applicationUser) {
        HttpHeaders headers = new HttpHeaders();
        try {
           userService.save(applicationUser);
           return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(
                   "user create"), headers, HttpStatus.CREATED);
       }
        catch (Exception e){
            return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(
                    "user not created"), headers, HttpStatus.CONFLICT);
        }

    }


    @PostMapping("/role")
    public String registration(@Valid @RequestBody List<Role> role) {

        for(Role r : role){
            roleRepository.save(r);
        }

        return "role saved";
    }

    @GetMapping("/signout")
    public ResponseEntity<AuthenticationResponse> logout(@RequestHeader("Authorization") String token){
        HttpHeaders headers = new HttpHeaders();
        headers.remove("Authorization");
        String user = jwtTokenHandler.parseJwtToken(token);
        try {
            jwtTokenRepo.delete(new JwtToken(token.substring(7)));
            return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(
                    "logout-success"), headers, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(
                    "logout-fail"), headers, HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestHeader("Authorization") String token) {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(
                "authenticated"), headers, HttpStatus.CREATED);


    }

}
