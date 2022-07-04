package com.axinalis.noSqlDbs.service.impl;

import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.dto.Client;
import com.axinalis.noSqlDbs.entity.ClientEntity;
import com.axinalis.noSqlDbs.repository.BookRepository;
import com.axinalis.noSqlDbs.repository.UserRepository;
import com.axinalis.noSqlDbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.axinalis.noSqlDbs.service.DtoEntityMapper.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(@Autowired UserRepository userRepository, @Autowired BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Client> userList() {
        List<ClientEntity> userEntities = userRepository.findAll();
        List<Client> clients = new ArrayList<>(userEntities.size());

        for(ClientEntity clientEntity : userEntities){
            clients.add(mapUserEntityToDto(
                    clientEntity,
                    clientEntity
                            .getReadBooks().stream()
                            .map(bookId -> mapBookEntityToDto(bookRepository.findById(bookId).orElseThrow()))
                            .collect(Collectors.toList())));
        }

        return clients;
    }

    @Override
    public Client userById(long id) {
        ClientEntity clientEntity = userRepository.findById(id).orElseThrow();
        return mapUserEntityToDto(clientEntity, clientEntity
                .getReadBooks().stream()
                .map(bookId -> mapBookEntityToDto(bookRepository.findById(bookId).orElseThrow()))
                .collect(Collectors.toList())
        );
    }

    @Override
    public Client createUser(Client client) {
        ClientEntity returnedUser = userRepository.save(mapUserDtoToEntity(
                client,
                client.getReadBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toList())));
        return mapUserEntityToDto(returnedUser, returnedUser
                        .getReadBooks().stream()
                        .map(bookId -> mapBookEntityToDto(bookRepository.findById(bookId).orElseThrow()))
                        .collect(Collectors.toList())
                );
    }

    @Override
    public Client updateUser(long id, Client client) {
        ClientEntity clientEntity = userRepository.save(mapUserDtoToEntity(
                client,
                client.getReadBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toList())));
        return mapUserEntityToDto(
                clientEntity,
                clientEntity.getReadBooks().stream()
                        .map(bookId -> mapBookEntityToDto(bookRepository.findById(bookId).orElseThrow()))
                        .collect(Collectors.toList()));
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
