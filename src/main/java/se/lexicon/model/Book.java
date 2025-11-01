package se.lexicon.model;

public class Book {

    private String id;
    private String title;
    private String author;
    private boolean available;
    private Person borrower;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
        this.borrower = null;
    }

    public String getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getAuthor() { return this.author; }
    public boolean isAvailable() { return this.available; }
    public Person getBorrower() { return this.borrower; }


    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
        this.available = (borrower == null); //
    }


    public String getBookInformation() {
        String status = available
                ? "Available"
                : "Borrowed by " + borrower.getPersonInformation();
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", " + status;
    }

    // todo: needs completion
}
