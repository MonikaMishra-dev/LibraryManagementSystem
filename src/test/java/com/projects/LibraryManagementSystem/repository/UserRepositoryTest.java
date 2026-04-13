package com.projects.LibraryManagementSystem.repository;

import com.projects.LibraryManagementSystem.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        User user1 = User.builder().
                name("User1").
                email("user1.@gmail.com").
                build();
        userRepository.save(user1);

        User user2 = User.builder().
                name("User1").
                email("user2.@gmail.com").
                build();
        userRepository.save(user2);
    }

    @Test
    public void testFindByName(){
        List<User> userList = userRepository.findByName("User1");
        assertEquals(2,userList.size());
    }
}
