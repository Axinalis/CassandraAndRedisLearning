package com.axinalis.noSqlDbs.controller;

import com.axinalis.noSqlDbs.dto.Client;
import com.axinalis.noSqlDbs.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/user")
public class UserController {

    @Autowired
    private ClientService clientService;
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<Client> userList(){
        log.info("List of all users requested");
        return clientService.userList();
    }

    @GetMapping("{id}")
    public Client userById(@PathVariable long id){
        log.info("User with id {} was requested", id);
        return clientService.userById(id);
    }

    @PostMapping
    public Client createUser(@RequestBody Client client){
        log.info("Creating of user was requested. {}", client);
        return clientService.createUser(client);
    }

    @PutMapping("{id}")
    public Client updateUser(@PathVariable long id, @RequestBody Client client){
        log.info("Updating of user {} was requested. {}", id, client);
        return clientService.updateUser(id, client);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        log.info("Deleting of user {} was requested", id);
        clientService.deleteUser(id);
    }

}
