package com.projects.LibraryManagementSystem.service;

import com.projects.LibraryManagementSystem.dto.UserCreationRequest;
import com.projects.LibraryManagementSystem.dto.UserCreationResponse;
import com.projects.LibraryManagementSystem.dto.UserFilterResponse;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.enums.UserFilter;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.model.User;
import com.projects.LibraryManagementSystem.enums.UserType;
import com.projects.LibraryManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserCreationResponse addStudent(UserCreationRequest request) {
        User user = request.toUser();
        user.setUserType(UserType.STUDENT);
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
                    case EQUALS -> {return userRepository.findByEmail(value);}
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
}

/*some methods are present with JPA, we can call those methods directly from our service.
But there are certain methods which we might need and JPA does not have those then
in that case we have to create those methods in the repository and call them
from our service. **/

