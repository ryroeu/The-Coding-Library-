//Project Name: The Coding Library
//Author: Kenneth Elmore
//Date: 12/4/2025
//This class creates the attributes for each book.

package Library;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isOut;

    // Constructors for the "Book" Object
    public Book(String title, String author, String isbn) {
	this.title = title;
	this.author = author;
	this.isbn = isbn;
	this.isOut = false;
    }

// Getter/Setter methods to access the attributes
    public String getTitle() {
	return title;
    }

    public String getAuthor() {
	return author;
    }

    public String getIsbn() {
	return isbn;
    }

    public boolean isOut() {
	return isOut;
    }

//Method handling taking out books
    public void checkoutBook() {
	if (!isOut) {
	    isOut = true;
	    System.out.println("You have taken out: " + title);
	} else {
	    System.out.println("You have returned " + title + " Thank you!");
	}
    }

    @Override
    public String toString() {
	return "Book [Title: " + title + "\n Author: " + author + "\n ISBN: " + isbn + "\n Avalible: " + !isOut;
    }
}
