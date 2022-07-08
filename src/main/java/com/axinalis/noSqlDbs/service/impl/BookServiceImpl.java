package com.axinalis.noSqlDbs.service.impl;

import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.entity.BookEntity;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.service.BookService;
import com.axinalis.noSqlDbs.service.DtoEntityMapper;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.axinalis.noSqlDbs.service.DtoEntityMapper.mapBookDtoToEntity;
import static com.axinalis.noSqlDbs.service.DtoEntityMapper.mapBookEntityToDto;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private static Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    public BookServiceImpl(@Autowired BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> bookList() {
        return bookRepository.findAll().stream()
                .map(DtoEntityMapper::mapBookEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Book bookById(long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if(book.isEmpty()){
            log.warn("Requested book with id {} was not found", id);
            throw new RuntimeException("Book with this id is not found");
        } else {
            return mapBookEntityToDto(book.get());
        }
    }

    @Override
    public Book createBook(Book book) {
        BookEntity bookEntity = mapBookDtoToEntity(book);
        bookEntity.setBookId(Uuids.timeBased().timestamp());
        log.info("Book creating was started. {}", book);
        return mapBookEntityToDto(bookRepository.save(bookEntity));
    }

    @Override
    public Book updateBook(long id, Book book) {
        BookEntity bookEntity = mapBookDtoToEntity(book);
        bookEntity.setBookId(id);
        return mapBookEntityToDto(bookRepository.save(bookEntity));
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
