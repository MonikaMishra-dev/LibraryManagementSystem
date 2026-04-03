package com.projects.LibraryManagementSystem.service;

import com.projects.LibraryManagementSystem.model.Author;
import com.projects.LibraryManagementSystem.repository.AuthorRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public Author findAuthorFromDb(String authorEmail) {
        return authorRepository.findByEmail(authorEmail);
    }

    public Author saveMyAuthor(Author author) {
        return authorRepository.save(author);
    }
}
