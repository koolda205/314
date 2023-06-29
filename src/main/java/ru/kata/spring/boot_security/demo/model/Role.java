package ru.kata.spring.boot_security.demo.model;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "role")
//public class Role implements GrantedAuthority {
//
//    @Id
//    private Long id;
//    private String name;
//    @Transient
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//    public Role() {
//    }
//
//    public Role(Long id) {
//        this.id = id;
//    }
//
//    public Role(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String getAuthority() {
//        return getName();
//    }

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.USERS_READ)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_WRITE));

    private final Set<Permission> permission;

    Role(Set<Permission> permission) {
        this.permission = permission;
    }

    public Set<Permission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
