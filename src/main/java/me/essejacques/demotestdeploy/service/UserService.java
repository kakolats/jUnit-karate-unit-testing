package me.essejacques.demotestdeploy.service;

import me.essejacques.demotestdeploy.entity.User;

import java.util.List;

public interface UserService {
      User saveUser(User user) ;
      User fetchUserById(Long id);
      List<User> findAllUsers();

}
