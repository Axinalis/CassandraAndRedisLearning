package com.axinalis.noSqlDbs.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.Objects;

@Table("client")
public class ClientEntity {

    @PrimaryKey
    private Long clientId;
    @Column
    private String firstName;
    @Column
    private int age;
    @Column
    private List<Long> readBooks;

    public ClientEntity() {
    }

    public ClientEntity(Long userId, String firstName, int age, List<Long> readBooks) {
        this.clientId = userId;
        this.firstName = firstName;
        this.age = age;
        this.readBooks = readBooks;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        return age == that.age && Objects.equals(clientId, that.clientId) && Objects.equals(firstName, that.firstName) && Objects.equals(readBooks, that.readBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, firstName, age, readBooks);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + clientId +
                ", name='" + firstName + '\'' +
                ", age=" + age +
                ", readBooks=" + readBooks +
                '}';
    }
}
