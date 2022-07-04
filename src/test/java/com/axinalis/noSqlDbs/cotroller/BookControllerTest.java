package com.axinalis.noSqlDbs.cotroller;

import com.axinalis.noSqlDbs.controller.BookController;
import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.repository.UserRepository;
import com.axinalis.noSqlDbs.service.BookService;
import com.axinalis.noSqlDbs.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@ExtendWith(SpringExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private BookService bookService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testGettingListOfBooks() throws Exception {
        when(bookService.bookList()).thenReturn(Arrays.asList(getNewBook()));

        mockMvc.perform(get("/library/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Sotnikau"))
                .andExpect(jsonPath("$[0].author").value("Vasil Bykov"));
    }

    @Test
    public void testGettingABook() throws Exception {
        when(bookService.bookById(1)).thenReturn(getNewBook());

        mockMvc.perform(get("/library/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Sotnikau"))
                .andExpect(jsonPath("author").value("Vasil Bykov"));
    }

    @Test
    public void testCreatingABook() throws Exception {
        Book bookToCreate = getNewBook();
        Book bookToReturn = getNewBook();
        bookToCreate.setId(5L);
        when(bookService.createBook(bookToCreate)).thenReturn(bookToReturn);

        mockMvc.perform(post("/library/book")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(bookToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Sotnikau"))
                .andExpect(jsonPath("author").value("Vasil Bykov"))
                .andExpect(jsonPath("id").value(1L));

        verify(bookService, times(1)).createBook(bookToCreate);
    }

    @Test
    public void testUpdatingABook() throws Exception {
        Book updatedBook = getNewBook();
        when(bookService.updateBook(1, updatedBook)).thenReturn(updatedBook);

        mockMvc.perform(put("/library/book/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Sotnikau"))
                .andExpect(jsonPath("author").value("Vasil Bykov"));
    }

    @Test
    public void testDeletingABook() throws Exception {
        mockMvc.perform(delete("/library/book/1"))
                .andExpect(status().isOk());

        verify(bookService).deleteBook(1);
    }

    private Book getNewBook(){
        return new Book(1L, "Sotnikau", "Vasil Bykov");
    }
}
