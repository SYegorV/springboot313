package com.company.service;

import com.company.models.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with id %s not found", id));
        } else {
            return userById.get();
        }
    }

    @Override
    public Optional<User> getUserByMail(String mail) {
        return userRepository.getUserByMail(mail);
    }

    @Override
    public void addUser(User newUser) {
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        userRepository.saveAndFlush(newUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, User userForUpdate) {
        if (!getById(id).getPassword().equals(userForUpdate.getPassword())) {
            userForUpdate.setPassword(new BCryptPasswordEncoder().encode(userForUpdate.getPassword()));
        }
        userRepository.saveAndFlush(userForUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersList() {
        return userRepository.findAll();
    }
}
