package com.projects.LibraryManagementSystem.repository;

import com.projects.LibraryManagementSystem.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setUp(){
        Author author1 = Author.builder().
                id("1").
                email("author1@gmail.com").
                name("Author1").
                build();
        authorRepository.save(author1);

        Author author2 = Author.builder().
                id("2").
                email("author2@gmail.com").
                name("Author2").
                build();
        authorRepository.save(author2);
    }

    @Test
    void testFindByEmail(){
        Author author = authorRepository.findByEmail("author1@gmail.com");
        assertEquals("Author1", author.getName());
    }
}
