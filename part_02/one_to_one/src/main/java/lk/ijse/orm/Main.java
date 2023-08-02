package lk.ijse.orm;

import lk.ijse.orm.entity.Author;
import lk.ijse.orm.entity.Book;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Save Author");
        System.out.println("===========");
        String authorId = null;
        String authorName = null;
        String address = null;

        try {
            System.out.println("Enter the Author Id : ");
            authorId = reader.readLine();

            System.out.println("Enter the Author name : ");
            authorName = reader.readLine();

            System.out.println("Enter the Author address : ");
            address = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Save Book");
        System.out.println("===========");
        String bookId = null;
        String bookName = null;
        double price = 0;

        try {
            System.out.println("Enter the book Id : ");
            bookId = reader.readLine();

            System.out.println("Enter the book name : ");
            bookName = reader.readLine();

            System.out.println("Enter the book price : ");
            price = Double.parseDouble(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("Invalid input. Please input double value.");
            System.exit(0);
        }

        Author author = new Author(authorId,authorName,address);

        Book book = new Book(bookId,bookName,price,author);


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
