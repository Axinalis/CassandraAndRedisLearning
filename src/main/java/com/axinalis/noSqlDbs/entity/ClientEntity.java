package com.axinalis.noSqlDbs.entity;

import com.axinalis.noSqlDbs.repository.UserBooksConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String name;
    private int age;
    @Convert(converter = UserBooksConverter.class)
    private List<Long> readBooks;

    public ClientEntity() {
    }

    public ClientEntity(Long userId, String name, int age, List<Long> readBooks) {
        this.clientId = userId;
        this.name = name;
        this.age = age;
        this.readBooks = readBooks;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Long> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(List<Long> readBooks) {
        this.readBooks = readBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return age == that.age && Objects.equals(clientId, that.clientId) && Objects.equals(name, that.name) && Objects.equals(readBooks, that.readBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, name, age, readBooks);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + clientId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", readBooks=" + readBooks +
                '}';
    }
}
