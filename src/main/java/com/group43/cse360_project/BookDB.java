/* BookDB - Book Database
 * ----------------------
 * Handles database interactions with books.db
 * Format:
 *      title:
 */

package com.group43.cse360_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BookDB {
    public static enum BookCondition {
        NEW,
        USED
    }

    // Creates a new book object from information stored in the database
    // Returns null if no book information is available
    public static Book getBook(String name) throws IOException {
        return null;
    }

    public static void addNewBook() throws IOException {

    }

    // Updates a book entry in the database from a given book class
    public static void updateBook(Book book) throws IOException {

    }

    public static boolean bookExists(String name) {
        return false;
    }

    // Returns an array of all the books listed by a specified seller
    public static Book[] getBooksBySeller(int id) throws IOException {

    }

    // Returns an array of all books of a specified condition
    public static Book[] getBooksByCondition(BookCondition condition) throws IOException {

    }

    public static Book[] getBooksByPriceRange(float low, float high) throws IOException {

    }
}
