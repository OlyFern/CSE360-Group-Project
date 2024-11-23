/* BookDB - Book Database
 * ----------------------
 * Handles database interactions with books.db
 * Format:
 *      title:author:genre:condition:seller:price:quantity:status
 */

package com.group43.cse360_project;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class BookDB {

    // Creates new book entry in the database (ListingStatus always set to PENDING)
    public static void addNewBook(String key, String title, String author, BookGenre genre, BookCondition cond,
                                  String seller, float price, int quant) throws IOException {
        float roundedValue = Math.round(price * 100) / 100.0f;
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                "src/main/resources/com/group43/cse360_project/books.db",
                true));
        writer.write(
                        key + ":" +
                        title + ":" +
                        author + ":" +
                        BookGenre.getBookGenreDBFlag(genre) + ":" +
                        BookCondition.getBookConditionDBFlag(cond) + ":" +
                        seller + ":" +
                        Float.toString(roundedValue) + ":" +
                        Integer.toString(quant) + ":" +
                        ListingStatus.getListingStatusDBFlag(ListingStatus.PENDING) + "\n"
        );
        writer.close();
    }

    // Updates a book entry in the database from a given book class (Checks key and title)
    public static void updateBook(Book book) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        StringBuffer buffer = new StringBuffer();
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith(book.getKey() + ":" + book.getTitle())) {
                line = book.getKey() + ":" + book.getTitle() + ":" + book.getAuthor() + ":" +
                        BookGenre.getBookGenreDBFlag(book.getGenre()) + ":" +
                        BookCondition.getBookConditionDBFlag(book.getCondition()) + ":" +
                        book.getSeller() + ":" + Float.toString(book.getPrice()) + ":" +
                        Integer.toString(book.getQuantity()) +
                        ListingStatus.getListingStatusDBFlag(book.getListingStatus());
            }

            buffer.append(line);
            buffer.append("\n");
        }

        FileOutputStream out = new FileOutputStream("src/main/resources/com/group43/cse360_project/books.db");
        out.write(buffer.toString().getBytes());
        out.close();
    }

    // Returns a linked list of all books in the database
    public static LinkedList<Book> getAllBooks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/books.db"));
        LinkedList<Book> books = new LinkedList<Book>();
        String line;
        String[] book;
        while ((line = reader.readLine()) != null) {
            book = line.split(":");
            System.out.println(Arrays.toString(book));
            books.add(new Book(
                    book[0],                                            // key
                    book[1],                                            // title
                    book[2],                                            // author
                    BookGenre.parseBookGenreDBFlag(book[3]),            // genre
                    BookCondition.parseBookConditionDBFlag(book[4]),    // condition
                    book[5],                                            // seller
                    Float.parseFloat(book[6]),                          // price
                    Integer.parseInt(book[7]),                          // quantity
                    ListingStatus.parseListingStatusDBFlag(book[8])     // listing status
            ));
        }
        reader.close();
        return books;
    }

    // Returns a linked list of all books in the given list with a title matching the one provided
    public static LinkedList<Book> getBooksByTitle(LinkedList<Book> books, String title) {
        LinkedList<Book> result = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getTitle().compareToIgnoreCase(title) == 0)
                result.add(book);
        }
        return result;
    }

    // Returns a linked list of all books in the given list with an author matching the one provided
    public static LinkedList<Book> getBooksByAuthor(LinkedList<Book> books, String author) {
        LinkedList<Book> result = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getAuthor().compareToIgnoreCase(author) == 0)
                result.add(book);
        }
        return result;
    }

    // Returns a list of all the books in the given list with a seller matching the one provided
    public static LinkedList<Book> getBooksBySeller(LinkedList<Book> books, String seller) {
        LinkedList<Book> result = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getSeller().compareToIgnoreCase(seller) == 0)
                result.add(book);
        }
        return result;
    }

    // Returns a list of all books in the given list with a condition matching the one provided
    public static LinkedList<Book> getBooksByCondition(LinkedList<Book> books, BookCondition condition) {
        LinkedList<Book> result = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getCondition() == condition)
                result.add(book);
        }
        return result;
    }

    // Returns a list of all books in the given list with a genre matching the one specified
    public static LinkedList<Book> getBooksByGenre(LinkedList<Book> books, BookGenre genre) {
        LinkedList<Book> result = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getGenre() == genre)
                result.add(book);
        }
        return result;
    }

    // Returns a list of all books in the given list with a price within the specified price range
    public static LinkedList<Book> getBooksByPriceRange(LinkedList<Book> books, float low, float high) {
        LinkedList<Book> result = new LinkedList<Book>();
        for (Book book : books) {
            float price = book.getPrice();
            if (price >= low && price <= high)
                result.add(book);
        }
        return result;
    }

    // Returns a list of books within the given list with a listing status matching the one specified
    public static LinkedList<Book> getBooksByListingStatus(LinkedList<Book> books, ListingStatus status) {
        LinkedList<Book> result = new LinkedList<Book>();
        for (Book book : books) {
            if (book.getListingStatus() == status)
                result.add(book);
        }
        return result;
    }
}