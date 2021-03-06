package com.user.management.UserManagement.Modals;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String username;

    private String password;


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"))
//    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


//    public Set<Role> getRoles() {
//        return roles;
//    }
//

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ApplicationUser() {
        super();
    }
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}
