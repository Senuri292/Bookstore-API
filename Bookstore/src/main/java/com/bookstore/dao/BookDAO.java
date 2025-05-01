/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.dao;

import com.bookstore.model.Book;
import java.util.ArrayList;
import java.util.List;

// public class to handle book operations
public class BookDAO {
    // array list to store books
    private static List<Book> books = new ArrayList<>();
    
    static {
        // add 5 books to the array every time the program runs
        books.add(new Book(101, "The Hobbit", 201, "9780261103344", 1937, 14.99, 12));
        books.add(new Book(102, "Harry Potter and the Sorcerer’s Stone", 202, "9780439708180", 1997, 12.99, 15));
        books.add(new Book(103, "The Name of the Wind", 203, "9780756404741", 2007, 16.99, 10));
        books.add(new Book(104, "Mistborn: The Final Empire", 204, "9780765311788", 2006, 13.99, 8));
        books.add(new Book(105, "The Way of Kings", 205, "9780765326355", 2010, 19.99, 5));
    }
    
    // method to return all books
    public List<Book> getAllBooks(){
        return books;
    }
    
    // method to return a book by given Id
    public Book getBooksById(int bookId){
        for (Book book : books) {// for-each loop to iterrate through books
            if(book.getBookId() == bookId){
                return book;
            }            
        }
        return null;
    }
    
    // method to add new book
    public void addBook(Book book){
        int newUserId = getNextBookId();
        book.setBookId(newUserId);
        books.add(book);
    }
    
    // method to update a book by Id
    public void updateBook(Book updatedBook){
        for (int i = 0; i < books.size(); i++){
            Book currentBook = books.get(i);
            if (currentBook.equals(updatedBook.getBookId())) {
                books.set(i, updatedBook);
                System.out.println("Book Updated");
                return;
            }
        }
    }
    
    // method to deleta a book by Id
    public void deleteBook(int bookId){
         books.removeIf(book -> book.getBookId() == bookId);
    }
    
    // method to find out what is the next Id available
    public int getNextBookId(){
        if (books.isEmpty()) {
            return 101; // Start with ID 101 if no books exist
        }
        
        int maxUserId = 0;
        
        for (int i = 0; i < books.size(); i++){
            int bookId = books.get(i).getBookId();
            if (bookId > maxUserId){
                maxUserId = bookId;
            }
        }
        return maxUserId + 1;
    }
}
