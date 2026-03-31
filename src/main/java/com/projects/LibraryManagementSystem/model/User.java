package com.projects.LibraryManagementSystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class User extends TimeStamps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 15,unique = true)
    private String phoneNo;

    @Column(length=50,unique = true,nullable = false)
    private String email;

    private String address;

    @Enumerated
    private UserStatus userStatus;

    @Enumerated
    private UserType userType;

    @Transient
    private String temp;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<Txn> txnList;
}

