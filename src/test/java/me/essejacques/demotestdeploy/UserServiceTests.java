package me.essejacques.demotestdeploy;


import me.essejacques.demotestdeploy.entity.User;
import me.essejacques.demotestdeploy.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceTests {

    private  UserServiceImpl userService ;

    @BeforeEach
    public void setUp(){
        userService = new UserServiceImpl();
    }

    @Test
    public void testAddUser(){
      User  testUser = new User(null, "Stephane", "Stephane@doe.com", "123456");
      User userSaved = userService.saveUser(testUser);
        Assertions.assertAll(
                ()-> Assertions.assertNotNull(userSaved.getId()),
                ()-> Assertions.assertEquals("Stephane", userSaved.getName()),
                ()-> Assertions.assertEquals("Stephane@doe.com", userSaved.getAdresse()),
                ()-> Assertions.assertEquals("123456", userSaved.getPassword())
        );
    }

    @Test
    public  void findByIdNotFound(){
        Assertions.assertThrows(
                RuntimeException.class, ()-> userService.fetchUserById(10000L)
        );
    }

    @Test
    public void testFindUserByIdFound() {
        // Assuming you have a method to seed or mock data
        User expectedUser = new User(1L, "Stephane", "Stephane@doe.com", "123456");

        User foundUser = userService.fetchUserById(1L);
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedUser.getId(), foundUser.getId()),
                () -> Assertions.assertEquals(expectedUser.getName(), foundUser.getName()),
                () -> Assertions.assertEquals(expectedUser.getAdresse(), foundUser.getAdresse()),
                () -> Assertions.assertEquals(expectedUser.getPassword(), foundUser.getPassword())
        );
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userService.findAllUsers();
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertEquals(3, users.size());
        Assertions.assertTrue(users.stream().anyMatch(u -> "Stephane".equals(u.getName())));
    }


}
