package com.ednach.app.service.impl;

import com.ednach.app.model.Role;
import com.ednach.app.repository.RoleRepository;
import com.ednach.app.service.RoleService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        deleteById(id);
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in RoleServiceImpl"));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        try {
            roleRepository.deleteById(id);
            doExpensiveWork();
        } catch (EmptyResultDataAccessException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void doExpensiveWork() throws InterruptedException {
        Thread.sleep(5000);
        throw new RuntimeException();
    }
}
