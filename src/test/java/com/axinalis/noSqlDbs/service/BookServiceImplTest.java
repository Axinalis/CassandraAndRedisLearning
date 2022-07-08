package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.entity.BookEntity;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.service.impl.BookServiceImpl;
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
        List<Book> books = Arrays.asList(getNewBook());
        List<BookEntity> bookEntities = Arrays.asList(mapBookDtoToEntity(getNewBook()));
        when(bookRepository.findAll()).thenReturn(bookEntities);

        assertEquals(books, bookServiceImpl.bookList());
    }

    @Test
    public void testGettingBookById(){

        when(bookRepository.findById(1L)).thenReturn(Optional.of(mapBookDtoToEntity(getNewBook())));

        assertEquals(getNewBook(), bookServiceImpl.bookById(1));
    }

    @Test
    public void testCreatingNewBook(){
        BookEntity bookToCreate = mapBookDtoToEntity(getNewBook());
        Book bookFromController = getNewBook();
        when(bookRepository.save(bookToCreate)).thenReturn(bookToCreate);

        assertEquals(bookFromController, bookServiceImpl.createBook(bookFromController));
    }

    @Test
    public void testUpdatingBook(){
        Book book = getNewBook();
        BookEntity bookEntity = mapBookDtoToEntity(book);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        assertEquals(book, bookServiceImpl.updateBook(1, book));
    }

    @Test
    public void testDeletingBook(){
        bookServiceImpl.deleteBook(1);

        verify(bookRepository).deleteById(1L);
    }

    private Book getNewBook(){
        return new Book(1L, "Otsy i deti", "Ivan Turgenev");
    }
}
