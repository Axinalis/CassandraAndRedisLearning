package com.axinalis.noSqlDbs.dto;

import java.util.List;
import java.util.Objects;

public class Client {

    private Long id;
    private String name;
    private int age;
    private List<Book> readBooks;

    public Client() {
    }

    public Client(Long id, String name, int age, List<Book> readBooks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.readBooks = readBooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Book> getReadBooks(){
        return this.readBooks;
    }

    public void setReadBooks(List<Book> readBooks){
        this.readBooks = readBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && age == client.age && Objects.equals(name, client.name) && Objects.equals(readBooks, client.readBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, readBooks);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", readBooks=" + readBooks +
                '}';
    }
}
