package com.user.management.UserManagement.Repositories;

import com.user.management.UserManagement.Modals.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepo extends JpaRepository<JwtToken, String> {

}
