package com.projects.LibraryManagementSystem.service.impl;

import com.projects.LibraryManagementSystem.dto.requestdtos.BookCreationRequest;
import com.projects.LibraryManagementSystem.dto.responsedtos.BookCreationResponse;
import com.projects.LibraryManagementSystem.enums.BookType;
import com.projects.LibraryManagementSystem.model.Author;
import com.projects.LibraryManagementSystem.model.Book;
import com.projects.LibraryManagementSystem.repository.AuthorRepository;
import com.projects.LibraryManagementSystem.repository.BookRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookService bookService;

    @Test
    void testAddBook(){
        BookCreationRequest request = BookCreationRequest.builder().
                bookNo("bookNo1").
                bookSecurityAmount(100.0).
                bookType(BookType.EDUCATIONAL).
                bookTitle("bookTitle1").
                build();
        Book book = request.toBook();
        Author author = Author.builder().name("author1").email("author1@gmail.com").build();
        when(authorService.findAuthorFromDb(any())).thenReturn(author);
        book.setAuthor(author);
        Book responseBook = Book.builder().bookNo("bookNo1").title("bookTitle1").build();
        when(bookRepository.save(any(Book.class))).thenReturn(responseBook);
        BookCreationResponse response = bookService.addBook(request);
        assertEquals("bookNo1",response.getBookNo());
        assertEquals("bookTitle1",response.getBookTitle());
    }
    @Test
    void testAddBookNullAuthor(){
        BookCreationRequest request = BookCreationRequest.builder().
                bookNo("bookNo1").
                bookSecurityAmount(100.0).
                bookType(BookType.EDUCATIONAL).
                bookTitle("bookTitle1").
                authorName("xyz").authorEmail("abc").
                build();
        Book book = request.toBook();
        when(authorService.findAuthorFromDb(any())).thenReturn(null);
        Author author = Author.builder().name(request.getAuthorName()).email(request.getAuthorEmail()).build();
        book.setAuthor(author);
        Book responseBook = Book.builder().bookNo("bookNo1").title("bookTitle1").build();
        when(bookRepository.save(any(Book.class))).thenReturn(responseBook);
        BookCreationResponse response = bookService.addBook(request);
        assertEquals("bookNo1",response.getBookNo());
        assertEquals("bookTitle1",response.getBookTitle());
    }
}
