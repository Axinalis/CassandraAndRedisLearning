package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.dto.Client;

import java.util.List;

public interface ClientService {

    List<Client> userList();
    Client userById(long id);
    Client createUser(Client client);
    Client updateUser(long id, Client client);
    void deleteUser(long id);

}
