package com.projects.LibraryManagementSystem.repository;

import com.projects.LibraryManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

//    search via name, search like name
//    There are 3 ways of defining queries on our own


//    1. we are just going to write the method name based on the query to be performed
//    and hibernate will itself understand it and return us the response. Here we will
//    not be required to write the query manually.

    List<User> findByName(String name);
    List<User> findByNameLike(String name);
    List<User> findByNameIn(List<String> names);
    List<User> findByNameContains(String name);

    List<User> findByEmail(String email);
    List<User> findByEmailLike(String email);
    List<User> findByEmailIn(List<String> email);
    List<User> findByEmailContains(String email);

    List<User> findByPhoneNo(String phone);
    List<User> findByPhoneNoLike(String phone);
    List<User> findByPhoneNoIn(List<String> phone);
    List<User> findByPhoneNoContains(String phone);



//    2. 2nd way of writing the query is
//    nativeQuery is a boolean value such that if it is true  hibernate will not check
//    the query at the compile time and the query will directly run on the mysql.
//    but if this value is false then hibernate will check the query at the compile time itself,
//    if everything is f9, it will compile else it will give the error.

    @Query(value = "select u from User u where name = :name",nativeQuery = false)
    List<User> retrieveUserViaName(String name);

//    3. 3rd way of writing the query is

    @Query(value = "select * from user where name = :name",nativeQuery = true)
    List<User> retrieveUserViaNameNative(String name);
}
