package com.projects.LibraryManagementSystem.repository;

import com.projects.LibraryManagementSystem.enums.BookType;
import com.projects.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByTitle(String title);
    List<Book> findByTitleLike(String title);
    List<Book> findByTitleContains(String title);


    List<Book> findByBookNo(String bookNo);
    List<Book> findByBookNoLike(String bookNo);
    List<Book> findByBookNoContains(String bookNo);

    List<Book> findByBookType(BookType bookType);
    List<Book> findByBookTypeContains(BookType bookType);

    List<Book> findBySecurityAmount(Double securityAmount);
    List<Book> findBySecurityAmountLessThan(Double securityAmount);
    List<Book> findBySecurityAmountLessThanEqual(Double securityAmount);

}
