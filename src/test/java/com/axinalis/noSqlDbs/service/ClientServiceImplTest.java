package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.dto.Client;
import com.axinalis.noSqlDbs.entity.ClientEntity;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.repository.UserRepository;
import com.axinalis.noSqlDbs.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.axinalis.noSqlDbs.service.DtoEntityMapper.mapBookDtoToEntity;
import static com.axinalis.noSqlDbs.service.DtoEntityMapper.mapUserDtoToEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    private ClientServiceImpl clientServiceImpl;

    @BeforeEach
    public void setup() {
        this.clientServiceImpl = new ClientServiceImpl(userRepository, bookRepository);
    }

    @Test
    public void testGettingAllUsers(){
        Client client = getNewUser();
        List<Book> books = getNewBooks();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(0))));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(1))));
        when(userRepository.findAll()).thenReturn(
                Arrays.asList(mapUserDtoToEntity(
                        client,
                        books.stream().map(Book::getId).collect(Collectors.toList())
                ))
        );

        assertEquals(Arrays.asList(client), clientServiceImpl.userList());
    }

    @Test
    public void testGettingUserById(){
        Client client = getNewUser();
        List<Book> books = getNewBooks();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(0))));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(1))));
        when(userRepository.findById(1L)).thenReturn(Optional.of(mapUserDtoToEntity(
                client,
                books.stream().map(Book::getId).collect(Collectors.toList())))
        );

        assertEquals(client, clientServiceImpl.userById(1L));
    }
    /*
    I don't know how to write this test
    @Test
    public void testCreatingUser(){
        Client clientFromController = getNewUser();
        ClientEntity clientEntity = mapUserDtoToEntity(clientFromController, Arrays.asList(1L, 2L));
        clientFromController.setId(null);
        Client clientFromRepository = getNewUser();
        List<Book> books = getNewBooks();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(0))));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(1))));
        when(userRepository.save(clientEntity)).thenReturn(mapUserDtoToEntity(clientFromRepository, Arrays.asList(1L, 2L)));

        assertEquals(clientFromRepository, userServiceImpl.createUser(clientFromRepository));
    }
     */

    @Test
    public void testUpdatingUser(){
        Client client = getNewUser();
        List<Book> books = getNewBooks();
        ClientEntity clientEntity = mapUserDtoToEntity(client, List.of(1L, 2L));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(0))));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(mapBookDtoToEntity(books.get(1))));
        when(userRepository.save(clientEntity)).thenReturn(clientEntity);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mapUserDtoToEntity(client, List.of(1L, 2L))));

        assertEquals(client, clientServiceImpl.updateUser(1L, client));
    }

    @Test
    public void testDeletingUser(){
        clientServiceImpl.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    private Client getNewUser(){
        return new Client(1L, "Maxim", 26, getNewBooks());
    }

    private List<Book> getNewBooks(){
        return Arrays.asList(new Book(1L, "Metro 2033", "Dmitry Gluhovskij"),
                new Book(2L, "Kalasy pad syarpom tvaim", "Ulagzimir Karatkevich"));
    }
}
