package com.pidSpringBoot.pidSpringBoot.user;

import com.pidSpringBoot.pidSpringBoot.Representation.Representation;
import com.pidSpringBoot.pidSpringBoot.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    @NotEmpty(message = "The firstname must not be empty.")
    @Size(min=2, max=60, message = "The firstname must be between 2 and 60 characters long.")
    @Column(name = "firstname")
    private String firstName;
    @NotEmpty(message = "The lastname must not be empty.")
    @Size(min=1, max=60, message = "The lastname must be between 2 and 60 characters long.")
    @Column(name = "lastname")
    private String lastName;
    @NotEmpty(message = "The firstname must not be empty.")
    @Size(min=2, max=60, message = "The firstname must be between 2 and 60 characters long.")
    private String email;

    private String langue;
    private LocalDateTime created_at;
    @ManyToMany(mappedBy = "users")
    private List<Representation> representations = new ArrayList<>();

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)

    private Set<Role> roles = new HashSet<>();

    public String allRolesUser(){
        ArrayList tab = new ArrayList<>();
        this.roles.forEach( role -> tab.add(role.getId()));
        return tab.toString();
    }

    public User() {}

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public User(String login, String password, String firstName, String lastName, String email, String langue) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langue = langue;
        this.created_at = LocalDateTime.now();
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
    public User addRole(Role role) {
        if(!this.roles.contains(role)) {
            this.roles.add(role);
            role.addUser(this);
        }

        return this;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue.name();
    }

    public List<Representation> getRepresentations() {
        return representations;
    }
    public User addRepresentation(Representation representation) {
        if(!this.representations.contains(representation)) {
            this.representations.add(representation);
            representation.addUser(this);
        }

        return this;
    }

    public User removeRepresentation(Representation representation) {
        if(this.representations.contains(representation)) {
            this.representations.remove(representation);
            representation.getUsers().remove(this);
        }

        return this;
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
