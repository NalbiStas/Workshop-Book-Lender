package se.lexicon;

import se.lexicon.model.Book;
import se.lexicon.model.Person;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // --- People ---
        Person[] people = {
                new Person("Zhanti", "Stas"),
                new Person("Narin", "Stas")
        };

        Book[] library = {
                new Book("B001", "Letters from a Stoic", "Seneca"),
                new Book("B002", "Meditations", "Marcus Aurelius"),
                new Book("B003", "War and Peace", "Leo Tolstoy"),
                new Book("B004", "Anna Karenina", "Leo Tolstoy"),
                new Book("B005", "The Brothers Karamazov", "Fyodor Dostoevsky"),
                new Book("B006", "Crime and Punishment", "Fyodor Dostoevsky")
        };

        try (Scanner sc = new Scanner(System.in)) {
            Person active = choosePerson(sc, people);
            boolean running = true;

            while (running) {
                System.out.println("\n===== Menu (Active: " + active.getPersonInformation() + ") =====");
                System.out.println("1) Borrow a book");
                System.out.println("2) Return a book");
                System.out.println("3) Show books (available & borrowed)");
                System.out.println("4) Switch person");
                System.out.println("0) Exit");
                System.out.print("Choose: ");

                String choice = sc.nextLine().trim();
                switch (choice) {
                    case "1": // Borrow
                        Book toBorrow = chooseFromAvailable(sc, library);
                        if (toBorrow != null) {
                            active.loanBook(toBorrow);
                        }
                        break;

                    case "2": // Return
                        Book toReturn = chooseFromBorrowed(sc, active);
                        if (toReturn != null) {
                            active.returnBook(toReturn);
                        }
                        break;

                    case "3": // Show books
                        showAllBooks(library);
                        System.out.println();
                        for (Person p : people) {
                            p.showBorrowedBooks();
                        }
                        break;

                    case "4": // Switch person
                        active = choosePerson(sc, people);
                        break;

                    case "0":
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }


    private static Person choosePerson(Scanner sc, Person[] people) {
        while (true) {
            System.out.println("\nSelect person:");
            for (int i = 0; i < people.length; i++) {
                System.out.println((i + 1) + ") " + people[i].getPersonInformation());
            }
            System.out.print("Enter number: ");
            String s = sc.nextLine().trim();
            try {
                int ix = Integer.parseInt(s) - 1;
                if (ix >= 0 && ix < people.length) return people[ix];
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid selection. Try again.");
        }
    }

    private static Book chooseFromAvailable(Scanner sc, Book[] library) {
        int count = 0;
        System.out.println("\nAvailable books:");
        for (int i = 0; i < library.length; i++) {
            if (library[i].isAvailable()) {
                count++;
                System.out.println((i + 1) + ") " + library[i].getBookInformation());
            }
        }
        if (count == 0) {
            System.out.println("No books are currently available.");
            return null;
        }
        System.out.print("Enter number to borrow (or press Enter to cancel): ");
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return null;
        try {
            int ix = Integer.parseInt(s) - 1;
            if (ix >= 0 && ix < library.length && library[ix].isAvailable()) {
                return library[ix];
            }
        } catch (NumberFormatException ignored) {}
        System.out.println("Invalid selection.");
        return null;
    }

    private static Book chooseFromBorrowed(Scanner sc, Person person) {
        Book[] books = person.getBorrowedBooks();
        int count = 0;

        System.out.println("\nYour borrowed books:");
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                count++;
                System.out.println((i + 1) + ") " + books[i].getBookInformation());
            }
        }
        if (count == 0) {
            System.out.println("You have no borrowed books.");
            return null;
        }

        System.out.print("Enter number to return (or press Enter to cancel): ");
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return null;

        try {
            int ix = Integer.parseInt(s) - 1;
            if (ix >= 0 && ix < books.length && books[ix] != null) {
                return books[ix];
            }
        } catch (NumberFormatException ignored) {}
        System.out.println("Invalid selection.");
        return null;
    }

    private static void showAllBooks(Book[] library) {
        System.out.println("\nAll books:");
        for (Book b : library) {
            System.out.println(" - " + b.getBookInformation());
        }
    }
}
