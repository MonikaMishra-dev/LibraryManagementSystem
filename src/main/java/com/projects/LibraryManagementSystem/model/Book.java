package com.projects.LibraryManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder

public class Book extends TimeStamps{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 20,unique = true,nullable = false)
    private String bookNo;

    private BookType bookType;

    @Column(nullable = false)
    private Double securityAmount;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Txn> txnList;
}
