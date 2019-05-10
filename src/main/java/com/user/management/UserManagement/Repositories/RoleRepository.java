package com.user.management.UserManagement.Repositories;

import com.user.management.UserManagement.Modals.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
