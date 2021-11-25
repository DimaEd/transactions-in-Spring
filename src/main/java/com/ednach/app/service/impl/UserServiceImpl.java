package com.ednach.app.service.impl;

import com.ednach.app.model.User;
import com.ednach.app.repository.UserRepository;
import com.ednach.app.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error in UserServiceImpl"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        try {
            userRepository.deleteById(id);
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
