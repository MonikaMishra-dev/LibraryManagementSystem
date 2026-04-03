package com.projects.LibraryManagementSystem.repository;

import com.projects.LibraryManagementSystem.model.Author;
import com.projects.LibraryManagementSystem.model.AuthorCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, AuthorCompositeKey> {

    Author findByEmail(String email);
}
