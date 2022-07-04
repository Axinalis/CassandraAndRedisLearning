package com.axinalis.noSqlDbs.service;

import com.axinalis.noSqlDbs.dto.Book;
import com.axinalis.noSqlDbs.dto.Client;
import com.axinalis.noSqlDbs.entity.BookEntity;
import com.axinalis.noSqlDbs.entity.ClientEntity;

import java.util.List;

public class DtoEntityMapper {

    public static Client mapUserEntityToDto(ClientEntity clientEntity, List<Book> readBooks){
        return new Client(clientEntity.getClientId(),
                clientEntity.getName(),
                clientEntity.getAge(),
                readBooks);
    }

    public static ClientEntity mapUserDtoToEntity(Client clientDto, List<Long> readBooks){
        return new ClientEntity(clientDto.getId(),
                clientDto.getName(),
                clientDto.getAge(),
                readBooks);
    }

    public static Book mapBookEntityToDto(BookEntity bookEntity){
        return new Book(bookEntity.getBookId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor());
    }

    public static BookEntity mapBookDtoToEntity(Book bookDto){
        return new BookEntity(bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor());
    }

}
