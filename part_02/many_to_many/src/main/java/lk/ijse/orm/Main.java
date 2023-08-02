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

        Author author1 = new Author();
        author1.setAuthorId("A001");
        author1.setName("J.K. Rowling");
        author1.setAddress("London");

        Author author2 = new Author();
        author2.setAuthorId("A002");
        author2.setName("Martin Wickramasingha");
        author2.setAddress("London");

        Book book1 = new Book();
        book1.setBookId("B001");
        book1.setName("Harry Potter");
        book1.setPrice(2500.0);

        Book book2 = new Book();
        book2.setBookId("B002");
        book2.setName("Harry Potter 2");
        book2.setPrice(3500.0);

        Book book3 = new Book();
        book3.setBookId("B003");
        book3.setName("Harry Potter 3");
        book3.setPrice(3000.0);

        Book book4 = new Book();
        book4.setBookId("B004");
        book4.setName("Madolduwa");
        book4.setPrice(1500.0);

        Book book5 = new Book();
        book5.setBookId("B005");
        book5.setName("Amba Yaluwo");
        book5.setPrice(3000.0);

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        author1.setBooks(books);
        author2.setBooks(books);

        book1.setAuthor(authors);
        book2.setAuthor(authors);
        book3.setAuthor(authors);
        book4.setAuthor(authors);
        book5.setAuthor(authors);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(author1);
            session.persist(author2);
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            session.persist(book4);
            session.persist(book5);
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
