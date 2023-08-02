package lk.ijse.orm.entity;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @Column(name = "book_Id")
    private String bookId;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    public Book() {
    }

    public Book(String bookId, String name, Double price, Author author) {
        this.bookId = bookId;
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
