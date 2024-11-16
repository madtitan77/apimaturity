package com.example.apimaturity.model;
import jakarta.persistence.*;
import java.util.Set;
import com.example.apimaturity.security.Role;




@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserAssessment> userAssessments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserClient> userClients;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role.RoleType role;

    @Column(name = "name")
    private String name;

    // getters and setters for the above fields
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public Integer getId() {
        return this.id;
    }


    
    public void setPassword(String password) {
        this.password = password;
    }

    public Role.RoleType getRole() {
        return role;
    }

    public void setRole(String roleName) {
        if (Role.RoleType.isValidRole(roleName)) {
            this.role = Role.RoleType.valueOf(roleName);
        } else {
            this.role = Role.RoleType.USER;
        }
    }

    public void setId(Integer userId) {
        this.id = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}