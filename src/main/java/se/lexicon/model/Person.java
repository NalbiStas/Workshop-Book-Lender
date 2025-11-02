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

    // todo: needs completion
}