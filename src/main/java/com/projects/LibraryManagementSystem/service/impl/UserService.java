package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.requestdtos.UserCreationRequest;
import com.projects.LibraryManagementSystem.dto.responsedtos.UserCreationResponse;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.enums.UserFilter;
import com.projects.LibraryManagementSystem.model.User;
import com.projects.LibraryManagementSystem.enums.UserType;
import com.projects.LibraryManagementSystem.repository.UserCacheRepository;
import com.projects.LibraryManagementSystem.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCacheRepository userCacheRepository;

    @Value("${student.authority}")
    private String studentAuthority;

    @Value("${admin.authority}")
    private String adminAuthority;

    public UserCreationResponse addStudent(UserCreationRequest request) {
        User user = request.toUser();
        user.setUserType(UserType.STUDENT);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthorities(studentAuthority);
        User userFromDb = userRepository.save(user);
        return UserCreationResponse.builder().
                userName(userFromDb.getName()).
                userEmail(userFromDb.getEmail()).
                userPhone(userFromDb.getPhoneNo()).
                userAddress(userFromDb.getAddress()).
                build();
    }

    public List<User> filter(UserFilter filterBy, Operator operator, String value) {

        switch (filterBy){
            case NAME:{
                switch (operator){
                    case EQUALS -> {return userRepository.findByName(value);}
//                    case IN -> {return userRepository.findByNameIn(values);}
                    case LIKE -> {return userRepository.findByNameLike("%"+value+"%");}
                    case CONTAINS -> {return userRepository.findByNameContains(value);}
                    case LESS_THAN -> {}
                    case LESS_THAN_EQUAL -> {}
                }
            }
            case EMAIL:{
                switch (operator){
//                    case EQUALS -> {return userRepository.findByEmail(value);}
                    case IN -> {}
                    case LIKE -> {return userRepository.findByEmailLike("%"+value+"%");}
                    case CONTAINS -> {return userRepository.findByEmailContains(value);}
                    case LESS_THAN -> {}
                    case LESS_THAN_EQUAL -> {}
                }
            }
            case PHONE_NO:{
                switch (operator){
                    case EQUALS -> {return userRepository.findByPhoneNo(value);}
                    case IN -> {}
                    case LIKE -> {return userRepository.findByPhoneNoLike("%"+value+"%");}
                    case CONTAINS -> {return userRepository.findByPhoneNoContains(value);}
                    case LESS_THAN -> {}
                    case LESS_THAN_EQUAL -> {}
                }
            }
        }
        return new ArrayList<>();
    }

    public User checkForValidUser(@NotBlank(message = "user email must not be blank") String userEmail) {
        return userRepository.findByEmail(userEmail);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userCacheRepository.getUser(email);
//        System.out.println("Authorities: " + user.getAuthorities());
//        System.out.println("Password: " + user.getPassword());
        if (user == null) {
            user = userRepository.findByEmail(email);
            if (user == null)
                throw new UsernameNotFoundException("User not found!!");

            userCacheRepository.setUser(email, user);
        }

        return user;
    }

    public UserCreationResponse addAdmin(UserCreationRequest request) {
        User user = request.toUser();
        user.setUserType(UserType.ADMIN);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthorities(adminAuthority);
        User userFromDb = userRepository.save(user);
        return UserCreationResponse.builder().
                userName(userFromDb.getName()).
                userEmail(userFromDb.getEmail()).
                userPhone(userFromDb.getPhoneNo()).
                userAddress(userFromDb.getAddress()).
                build();
    }
}

/*some methods are present with JPA, we can call those methods directly from our service.
But there are certain methods which we might need and JPA does not have those then
in that case we have to create those methods in the repository and call them
from our service. **/

