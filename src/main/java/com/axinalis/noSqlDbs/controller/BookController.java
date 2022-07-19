package com.axinalis.noSqlDbs.controller;

import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/book")
public class BookController {

    @Autowired
    private BookService bookService;

    private static Logger log = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public List<Book> bookList(){
        log.info("Requested books list");
        return bookService.bookList();
    }

    @GetMapping("{id}")
    public Book bookById(@PathVariable long id){
        log.info("Requested book with id {}", id);
        return bookService.bookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        log.info("Creating of new book is requested. {}", book);
        Book book1 = bookService.createBook(book);
        log.info("Book created. {}", book1);
        return book1;
    }

    @PutMapping("{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book){
        log.info("Update of book with id {} is requested. {}", id, book);
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable long id){
        log.info("Deleting of book {} is requested", id);
        bookService.deleteBook(id);
    }

}
