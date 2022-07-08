package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.entity.BookEntity;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.service.impl.BookServiceImpl;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.axinalis.noSqlDbs.service.DtoEntityMapper.mapBookDtoToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    public void setup() {
        bookServiceImpl = new BookServiceImpl(bookRepository);
    }

    @Test
    public void testGettingBooksList(){
        Book book = getNewBook();
        List<BookEntity> bookEntities = Arrays.asList(mapBookDtoToEntity(book));
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(bookEntities);

        assertEquals(books.size(), bookServiceImpl.bookList().size());
        assertEquals(books.get(0).getId(), bookServiceImpl.bookList().get(0).getId());
    }

    @Test
    public void testGettingBookById(){
        Book book = getNewBook();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mapBookDtoToEntity(book)));

        assertEquals(book.getId(), bookServiceImpl.bookById(1).getId());
    }
/*
    I don't know, how to write this test
    @Test
    public void testCreatingNewBook(){
        Book bookFromController = getNewBook();
        BookEntity bookToCreate = mapBookDtoToEntity(bookFromController);

        bookServiceImpl.createBook(bookFromController);

        verify(bookRepository).save(bookToCreate);

    }

 */

    @Test
    public void testUpdatingBook(){
        Book book = getNewBook();
        BookEntity bookEntity = mapBookDtoToEntity(book);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        assertEquals(book.getId(), bookServiceImpl.updateBook(book.getId(), book).getId());
    }

    @Test
    public void testDeletingBook(){
        bookServiceImpl.deleteBook(1);

        verify(bookRepository).deleteById(1L);
    }

    private Book getNewBook(){
        return new Book(Uuids.timeBased().timestamp(), "Otsy i deti", "Ivan Turgenev");
    }
}
