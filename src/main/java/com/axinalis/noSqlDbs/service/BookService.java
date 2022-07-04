package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.dto.Book;

import java.util.List;

public interface BookService {

    List<Book> bookList();
    Book bookById(long id);
    Book createBook(Book book);
    Book updateBook(long id, Book book);
    void deleteBook(long id);

}
