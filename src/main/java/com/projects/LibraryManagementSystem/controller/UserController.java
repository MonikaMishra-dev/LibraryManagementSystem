package com.projects.LibraryManagementSystem.controller;

import com.projects.LibraryManagementSystem.dto.UserCreationRequest;
import com.projects.LibraryManagementSystem.dto.UserCreationResponse;
import com.projects.LibraryManagementSystem.enums.Operator;
import com.projects.LibraryManagementSystem.enums.UserFilter;
import com.projects.LibraryManagementSystem.model.User;
import com.projects.LibraryManagementSystem.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addStudent")
    public UserCreationResponse addStudent(@RequestBody @Validated UserCreationRequest request) {
        return userService.addStudent(request);
    }

    @GetMapping("/filter")
    public List<User> filteredUser(@RequestParam("filterBy") UserFilter filterBy,
                                   @RequestParam("operator") Operator operator,
                                   @RequestParam("value") String value
//                                   @RequestParam("values") List<String> values
    ) {
//        return userService.filter(UserFilter.valueOf(filterBy),Operator.valueOf(operator), value);
        return userService.filter(filterBy,operator,value);

    }
}

//creation
//update
//search
//delete -> mark user status as inactive(Soft deletion)
// [does not delete the physical entry from the table but mark it as delete]

//DTO -> Whenever we are transferring an object say from front end to back end
// or vice versa then if we do not want to return the entire object but some part
// of the object then we create DTOs dor the same.