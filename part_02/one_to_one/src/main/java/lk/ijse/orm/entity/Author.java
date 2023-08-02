package lk.ijse.orm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Author {

    @Id
    @Column(name = "author_Id")
    private String authorId;

    @Column(name = "author_name")
    private String name;

    @Column(name = "author_address")
    private String address;

    public Author() {
    }

    public Author(String authorId, String name, String address) {
        this.authorId = authorId;
        this.name = name;
        this.address = address;
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
