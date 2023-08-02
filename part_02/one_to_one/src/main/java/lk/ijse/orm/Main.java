package lk.ijse.orm;

import lk.ijse.orm.entity.Author;
import lk.ijse.orm.entity.Book;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {

        Author author = new Author("A001","J.K. Rowling","London");

        Book book = new Book("B001","Harry Potter",2000.0,author);


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(author);
            session.persist(book);
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
