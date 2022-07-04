package com.axinalis.noSqlDbs.cotroller;

import com.axinalis.noSqlDbs.controller.UserController;
import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.dto.Client;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.repository.UserRepository;
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

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BookRepository bookRepository;

    @Test
    public void testGettingUsersInfo() throws Exception {
        when(userService.userList()).thenReturn(Arrays.asList(getNewUser()));

        mockMvc.perform(get("/library/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Anton"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].readBooks").isArray())
                .andExpect(jsonPath("$[0].readBooks[0].title").value("Matrin Iden"))
                .andExpect(jsonPath("$[0].readBooks[0].author").value("Jack London"))
                .andExpect(jsonPath("$[0].readBooks[1].title").value("Harry Potter and Room of secrets"))
                .andExpect(jsonPath("$[0].readBooks[1].author").value("Joanne Rowling"));
    }

    @Test
    public void testGettingUserById() throws Exception {
        when(userService.userById(1)).thenReturn(getNewUser());

        mockMvc.perform(get("/library/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Anton"))
                .andExpect(jsonPath("age").value(22))
                .andExpect(jsonPath("readBooks").isArray())
                .andExpect(jsonPath("readBooks[0].title").value("Matrin Iden"))
                .andExpect(jsonPath("readBooks[0].author").value("Jack London"))
                .andExpect(jsonPath("readBooks[1].title").value("Harry Potter and Room of secrets"))
                .andExpect(jsonPath("readBooks[1].author").value("Joanne Rowling"));
    }

    @Test
    public void testCreatingUser() throws Exception {
        Client clientToCreate = getNewUser();
        clientToCreate.setId(500L);
        Client createdClient = getNewUser();

        when(userService.createUser(clientToCreate)).thenReturn(createdClient);

        mockMvc.perform(post("/library/user")
                .contentType("application/json")
                .content(mapper.writeValueAsString(clientToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Anton"))
                .andExpect(jsonPath("age").value(22))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("readBooks").isArray())
                .andExpect(jsonPath("readBooks[0].title").value("Matrin Iden"))
                .andExpect(jsonPath("readBooks[0].author").value("Jack London"))
                .andExpect(jsonPath("readBooks[1].title").value("Harry Potter and Room of secrets"))
                .andExpect(jsonPath("readBooks[1].author").value("Joanne Rowling"));
    }

    @Test
    public void testUpdatingUser() throws Exception {
        Client updatedClient = getNewUser();

        when(userService.updateUser(1, updatedClient)).thenReturn(updatedClient);

        mockMvc.perform(put("/library/user/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Anton"))
                .andExpect(jsonPath("age").value(22))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("readBooks").isArray())
                .andExpect(jsonPath("readBooks[0].title").value("Matrin Iden"))
                .andExpect(jsonPath("readBooks[0].author").value("Jack London"))
                .andExpect(jsonPath("readBooks[1].title").value("Harry Potter and Room of secrets"))
                .andExpect(jsonPath("readBooks[1].author").value("Joanne Rowling"));
    }

    @Test
    public void testDeletingUser() throws Exception {
        mockMvc.perform(delete("/library/user/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1);
    }

    private Client getNewUser(){
        return new Client(1L, "Anton", 22, Arrays.asList(
                new Book(1L, "Matrin Iden", "Jack London"),
                new Book(2L, "Harry Potter and Room of secrets", "Joanne Rowling")
        ));
    }
}
