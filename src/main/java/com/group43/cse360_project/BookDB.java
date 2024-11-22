/* BookDB - Book Database
 * ----------------------
 * Handles database interactions with books.db
 * Format:
 *      title:author:genre:condition:seller:price:quantity:status
 */

package com.group43.cse360_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class BookDB {

    // Creates new book entry in the database (ListingStatus always set to PENDING)
    public static void addNewBook(String key, String title, String author, BookGenre genre, BookCondition cond,
                                  String seller, float price, int quant) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/com/group43/cse360_project/books.db"));
        writer.write(
                        key + ":" +
                        title + ":" +
                        author + ":" +
                        BookGenre.getBookGenreDBFlag(genre) + ":" +
                        BookCondition.getBookConditionDBFlag(cond) + ":" +
                        seller + ":" +
                        Float.toString(price) + ":" +
                        Integer.toString(quant) + ":" +
                        ListingStatus.getListingStatusDBFlag(ListingStatus.PENDING) + "\n"
        );
        writer.close();
    }

    // Updates a book entry in the database from a given book class (New quantity after a sale or listing)
    public static void updateQuantity(Book book, int oldQuantity) throws IOException {

    }


    public static LinkedList<Book> getAllBooks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            System.out.println(Arrays.toString(book));
            books.add(new Book(
                    book[0],                    // key
                    book[1],                    // title
                    book[2],                    // author
                    BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                    BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                    book[5],                    // seller
                    Float.parseFloat(book[6]),  // price
                    Integer.parseInt(book[7]),  // quantity
                    ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
            ));
        }
        reader.close();
        return books;
    }

    // Returns a linked list of all books of a specified title
    public static LinkedList<Book> getBooksByTitle(String title) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            if (title.compareToIgnoreCase(book[1]) == 0) {
                books.add(new Book(
                        book[0],                    // key
                        book[1],                    // title
                        book[2],                    // author
                        BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                        BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                        book[5],                    // seller
                        Float.parseFloat(book[6]),  // price
                        Integer.parseInt(book[7]),  // quantity
                        ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
                ));
            }
        }
        reader.close();
        return books;
    }

    // Returns a linked list of all books written by a specified author
    public static LinkedList<Book> getBooksByAuthor(String author) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            if (author.compareToIgnoreCase(book[2]) == 0) {
                books.add(new Book(
                        book[0],                    // key
                        book[1],                    // title
                        book[2],                    // author
                        BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                        BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                        book[5],                    // seller
                        Float.parseFloat(book[6]),  // price
                        Integer.parseInt(book[7]),  // quantity
                        ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
                ));
            }
        }
        reader.close();
        return books;
    }

    // Returns a list of all the books listed by a specified seller
    public static LinkedList<Book> getBooksBySeller(String seller) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            if (seller.compareToIgnoreCase(book[5]) == 0) {
                books.add(new Book(
                        book[0],                    // key
                        book[1],                    // title
                        book[2],                    // author
                        BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                        BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                        book[5],                    // seller
                        Float.parseFloat(book[6]),  // price
                        Integer.parseInt(book[7]),  // quantity
                        ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
                ));
            }
        }
        reader.close();
        return books;
    }

    // Returns a list of all books of a specified condition
    public static LinkedList<Book> getBooksByCondition(BookCondition condition) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            if (BookCondition.parseBookConditionDBFlag(book[4]) == condition) {
                books.add(new Book(
                        book[0],                    // key
                        book[1],                    // title
                        book[2],                    // author
                        BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                        BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                        book[5],                    // seller
                        Float.parseFloat(book[6]),  // price
                        Integer.parseInt(book[7]),  // quantity
                        ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
                ));
            }
        }
        reader.close();
        return books;
    }

    // Returns a list of all books of a specified genre
    public static LinkedList<Book> getBooksByGenre(BookGenre genre) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            if (BookGenre.parseBookGenreDBFlag(book[3]) == genre) {
                books.add(new Book(
                        book[0],                    // key
                        book[1],                    // title
                        book[2],                    // author
                        BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                        BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                        book[5],                    // seller
                        Float.parseFloat(book[6]),  // price
                        Integer.parseInt(book[7]),  // quantity
                        ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
                ));
            }
        }
        reader.close();
        return books;
    }

    // Returns a list of all books within a price range
    public static LinkedList<Book> getBooksByPriceRange(float low, float high) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        float price;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            price = Float.parseFloat(book[6]);
            if (price >= low && price <= high) {
                books.add(new Book(
                        book[0],                    // key
                        book[1],                    // title
                        book[2],                    // author
                        BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                        BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                        book[5],                    // seller
                        Float.parseFloat(book[6]),  // price
                        Integer.parseInt(book[7]),  // quantity
                        ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
                ));
            }
        }
        reader.close();
        return books;
    }

    // Returns a list of books matching a specified listing status
    public static LinkedList<Book> getBooksByListingStatus(ListingStatus status) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            if (ListingStatus.parseListingStatusDBFlag(book[8]) == status) {
                books.add(new Book(
                        book[0],                    // key
                        book[1],                    // title
                        book[2],                    // author
                        BookGenre.parseBookGenreDBFlag(book[3]),        // genre
                        BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                        book[5],                    // seller
                        Float.parseFloat(book[6]),  // price
                        Integer.parseInt(book[7]),  // quantity
                        ListingStatus.parseListingStatusDBFlag(book[8]) // listing status
                ));
            }
        }
        reader.close();
        return books;
    }
}