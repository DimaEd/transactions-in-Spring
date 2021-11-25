package com.ednach.app.service;

import com.ednach.app.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role save(Role role);

    void deleteById(Long id);

}
