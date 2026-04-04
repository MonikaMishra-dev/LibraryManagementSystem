package com.projects.LibraryManagementSystem.service;

import com.projects.LibraryManagementSystem.enums.BookFilter;
import com.projects.LibraryManagementSystem.service.impl.BookNoFilterImpl;
import com.projects.LibraryManagementSystem.service.impl.BookTitleFilterImpl;
import com.projects.LibraryManagementSystem.service.impl.BookTypeFilterImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookFilterFactory {

    private Map<BookFilter,BookFilterStrategy> strategies = new HashMap<>();

    public BookFilterFactory(BookNoFilterImpl bookNoFilter,
                             BookTitleFilterImpl bookTitleFilter,
                             BookTypeFilterImpl bookTypeFilter){

        strategies.put(BookFilter.TITLE,bookTitleFilter);
        strategies.put(BookFilter.BOOK_NO,bookNoFilter);
        strategies.put(BookFilter.BOOK_TYPE,bookTypeFilter);
    }
    public BookFilterStrategy getStrategy(BookFilter filter){
        return strategies.get(filter);
    }
}
