package se.lexicon.model;

public class Person {

    private static int sequencer = 0;

    private int id;
    private String firstName;
    private String lastName;
    private Book[] borrowedBooks;
    private int bookCount;

    public Person(String firstName, String lastName) {
        this.id = ++sequencer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.borrowedBooks = new Book[5];
        this.bookCount = 0;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Book[] getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void loanBook(Book book) {
        if (book.isAvailable()) {
            if (bookCount < borrowedBooks.length) {
                borrowedBooks[bookCount] = book;
                bookCount++;
                book.setBorrower(this);
                System.out.println(firstName + " borrowed \"" + book.getTitle() + "\"");
            } else {
                System.out.println(firstName + " cannot borrow more books (limit reached).");
            }
        } else {
            System.out.println("Sorry, \"" + book.getTitle() + "\" is not available.");
        }
    }




    // todo: needs completion
}