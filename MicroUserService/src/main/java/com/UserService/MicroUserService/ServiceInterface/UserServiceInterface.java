package com.UserService.MicroUserService.ServiceInterface;

import com.UserService.MicroUserService.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceInterface {

    public User createUser(User user);

    public List<User> findAll();


    public User findById(Long userId);

    public void deleteUser(Long userId);

    public User updateUser(Long userId,User user);
}
