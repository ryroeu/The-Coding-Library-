//Project Name: The Coding Library
//Author: Trinity Young
//Date: 12/5/2025
//This class manages the collection of books using ArrayList data structures.

import java.util.ArrayList;

public class LibraryCatalog {

    // Store all books
    private ArrayList<Book> books;

    // Constructor
    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    // Add a new Book to the catalog
    public void addBook(Book book) {
        books.add(book);
    }

    // List all books with their status
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the catalog.");
            return;
        }

        for (Book b : books) {
            String status = b.isCheckedOut() ? "Checked Out" : "Available";
            System.out.println(b.getTitle() + " by " + b.getAuthor() + " -- " + status);
        }
    }

    // Check out a book by title
    public void checkOutBook(String title) {
        Book book = findBook(title);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isCheckedOut()) {
            System.out.println("This book is already checked out.");
        } else {
            book.setCheckedOut(true);
            System.out.println("You have checked out \"" + book.getTitle() + "\".");
        }
    }

    // Return a book by title
    public void returnBook(String title) {
        Book book = findBook(title);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isCheckedOut()) {
            System.out.println("This book wasn't checked out.");
        } else {
            book.setCheckedOut(false);
            System.out.println("You have returned \"" + book.getTitle() + "\".");
        }
    }

    // Get the status of a book by title
    public void getBookStatus(String title) {
        Book book = findBook(title);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        String status = book.isCheckedOut() ? "Checked Out" : "Available";
        System.out.println("\"" + book.getTitle() + "\" is currently " + status + ".");
    }

    // Helper method: find a book by its title
    private Book findBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    // Get all books in the catalog
    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
