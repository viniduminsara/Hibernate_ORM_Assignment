package lk.ijse.orm;

import lk.ijse.orm.entity.Book;
import lk.ijse.orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        System.out.println("CRUD operation to single entity");
        System.out.println("===============================");
        System.out.println("[1] Save book");
        System.out.println("[2] Update book");
        System.out.println("[3] Delete book");
        System.out.println("[4] Search book");

        try {
            System.out.print("Enter your option: ");
            int option = Integer.parseInt(reader.readLine()); // Read an integer input from the terminal

            switch (option){
                case 1:
                    saveBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    searchBook();
                    break;
                default:
                    System.out.println("Invalid option");
                    System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }

    private static void searchBook() {
        System.out.println("Search Book");
        System.out.println("===========");

        try {
            System.out.println("Enter the book id to search: ");
            String bookId = reader.readLine();

            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            try {
                Book book = session.get(Book.class, bookId);
                if (book != null) {
                    System.out.println("Book id : "+book.getBookId());
                    System.out.println("Book name : "+book.getName());
                    System.out.println("Book price : "+book.getPrice());
                } else {
                    System.out.println("Book with ID " + bookId + " not found.");
                }
            } catch (Exception e) {
                transaction.rollback();
                System.out.println("Failed to delete the book. Error: " + e.getMessage());
            } finally {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteBook() {
        System.out.println("Delete Book");
        System.out.println("===========");

        try {
            System.out.println("Enter the book id to delete: ");
            String bookId = reader.readLine();

            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            try {
                Book book = session.get(Book.class, bookId);
                if (book != null) {
                    session.remove(book);
                    transaction.commit();
                    System.out.println("Book with ID " + bookId + " deleted successfully!");
                } else {
                    System.out.println("Book with ID " + bookId + " not found.");
                }
            } catch (Exception e) {
                transaction.rollback();
                System.out.println("Failed to delete the book. Error: " + e.getMessage());
            } finally {
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateBook() {
        System.out.println("Update Book");
        System.out.println("=========");
        String bookId = null;
        String bookName = null;
        double price = 0;

        try {
            System.out.println("Enter the book id : ");
            bookId = reader.readLine();

            System.out.println("Enter the book name : ");
            bookName = reader.readLine();

            System.out.println("Enter the book price : ");
            price = Double.parseDouble(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid price.");
            System.exit(0);
        }

        Book book = new Book(bookId,bookName,price);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(book);
            transaction.commit();
            System.out.println("Book updated successfully!");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Failed to update the book. Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    private static void saveBook() {
        System.out.println("Save Book");
        System.out.println("=========");
        String bookId = null;
        String bookName = null;
        double price = 0;

        try {
            System.out.println("Enter the book id : ");
            bookId = reader.readLine();

            System.out.println("Enter the book name : ");
            bookName = reader.readLine();

            System.out.println("Enter the book price : ");
            price = Double.parseDouble(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid price.");
            System.exit(0);
        }

        Book book = new Book(bookId,bookName,price);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(book);
            transaction.commit();
            System.out.println("Book saved successfully!");
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Failed to save the book. Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
