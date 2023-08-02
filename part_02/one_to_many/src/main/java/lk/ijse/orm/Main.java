package lk.ijse.orm;

import lk.ijse.orm.entity.Author;
import lk.ijse.orm.entity.Book;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Author author = new Author();
        author.setAuthorId("A001");
        author.setName("J.K. Rowling");
        author.setAddress("London");

        Book book1 = new Book();
        book1.setBookId("B001");
        book1.setName("Harry Potter");
        book1.setPrice(2500.0);
        book1.setAuthor(author);

        Book book2 = new Book();
        book2.setBookId("B002");
        book2.setName("Harry Potter 2");
        book2.setPrice(3500.0);
        book2.setAuthor(author);

        Book book3 = new Book();
        book3.setBookId("B003");
        book3.setName("Harry Potter 3");
        book3.setPrice(3000.0);
        book3.setAuthor(author);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        author.setBooks(books);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(author);
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            transaction.commit();
            System.out.println("Book and Author saved successfully!");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Failed to save the book. Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
