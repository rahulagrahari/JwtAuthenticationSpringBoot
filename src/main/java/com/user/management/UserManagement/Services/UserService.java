package com.user.management.UserManagement.Services;

import com.user.management.UserManagement.Modals.ApplicationUser;

public interface UserService {
    void save(ApplicationUser applicationUser);

    ApplicationUser findByUsername(String username);
}
