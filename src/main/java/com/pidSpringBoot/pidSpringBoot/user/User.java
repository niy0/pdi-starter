package com.pidSpringBoot.pidSpringBoot.user;

import com.pidSpringBoot.pidSpringBoot.role.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 30,unique = true)
    private String login;

    @Column(nullable = false,length = 255)
    private String password;

    @Column(nullable = false,length = 60)
    private String firstName;

    @Column(nullable = false,length = 60)
    private String lastName;

    @Column(nullable = false,length = 100)
    private String email;

    @Column(nullable = false)
    private String langue;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public String allRolesUser(){
        ArrayList tab = new ArrayList<>();
        this.roles.forEach( role -> tab.add(role.getId()));
        return tab.toString();
    }

    public User() {}

    public User(String login, String password, String firstName, String lastName, String email, String langue) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langue = langue;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean hasRole(String roleName) {
        Iterator<Role> it = roles.iterator();
        while(it.hasNext()) {
            Role role = it.next();
            if(role.getRole().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
    public void addRoles(Role roles) {
        this.roles.add(roles);
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue.name();
    }




    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", langue=" + langue +
                ", roles=" + roles +
                '}';
    }
}
