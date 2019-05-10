package com.user.management.UserManagement.Controllers;

import com.user.management.UserManagement.Modals.Role;
import com.user.management.UserManagement.Modals.ApplicationUser;
import com.user.management.UserManagement.Payload.AuthenticationResponse;
import com.user.management.UserManagement.Repositories.RoleRepository;
import com.user.management.UserManagement.Services.UserServiceImpl;
import com.user.management.UserManagement.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;



    @Autowired
    private UserValidator userValidator;

    @Autowired
    RoleRepository roleRepository;


    @PostMapping("/registration")
    public String registration(@Valid @RequestBody ApplicationUser applicationUser) {

        userService.save(applicationUser);

        return "welcome";
    }


    @PostMapping("/role")
    public String registration(@Valid @RequestBody List<Role> role) {

        for(Role r : role){
            roleRepository.save(r);
        }

        return "role saved";
    }

    @GetMapping("/authenticate")
    public AuthenticationResponse authenticate() {

    return new AuthenticationResponse("200", "Authenticated");


    }

}
