package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }


    public Role getRole(String userRole) {
        return roleRepository.findByUserRole(userRole);
    }



    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
