package lk.ijse.orm.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {

    @Id
    @Column(name = "author_Id")
    private String authorId;

    @Column(name = "author_name")
    private String name;

    @Column(name = "author_address")
    private String address;

    @ManyToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String authorId, String name, String address, List<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
