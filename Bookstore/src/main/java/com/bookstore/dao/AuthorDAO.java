/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.dao;

import com.bookstore.model.Author;
import com.bookstore.model.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthorDAO {
    //creating a hashmap to store author details 
    private static HashMap<Integer, Author> authors = new HashMap<>();
    // injecting BookDAO to retrieve the books list
    private BookDAO bookDAO;
    
    static{
        authors.put(201, new Author(201, "J.R.R.", "Tolkien", "Author of The Lord of the Rings."));
        authors.put(202, new Author(202, "J.K.", "Rowling", "Creator of the Harry Potter series."));
        authors.put(203, new Author(203, "Patrick", "Rothfuss", "Author of The Kingkiller Chronicle."));
        authors.put(204, new Author(204, "Brandon", "Sanderson", "Known for the Mistborn series."));
        authors.put(205, new Author(205, "Brandon", "Sanderson", "Creator of The Stormlight Archive."));
    }
    
    // method to return all authors
    public HashMap<Integer, Author> getAllAuthors(){
        return authors;
    }
    
    // method to return a author by given Id
    public Author getAuthorsById(int authorId) {
        // Direct lookup in hashmap for id
        return authors.get(authorId);
    }
    
    // method to return a author and their books by given Id
    public Map<Author, List<Book>> getAuthorsBooksById(int authorId) {
        Author author = authors.get(authorId);

        if (author == null) {
            return null;
        }
        //creating a list with all the books
        List<Book> books = bookDAO.getAllBooks();
        //creating a list to store author's books
        List<Book> authorBooks = new ArrayList<>();
        // iterating through each book and storing it in the list if author Id match
        for (Book book : books) {
            if (book.getAuthorId() == authorId) {
                authorBooks.add(book);
            }
        }

        // Use a map to return both author and the booklist
        Map<Author, List<Book>> authorsBooks = new HashMap<>();
        authorsBooks.put(author, authorBooks);
        return authorsBooks;
    }
    
    // method to add new author
    public void addAuthor(Author author){
        int newAuthorId = getNextAuthorId();
        author.setAuthorId(newAuthorId);
        authors.put(newAuthorId, author);
    }
    
    // method to update a author by Id
    public void updateAuthor(Author updatedAuthor) {
        // Get authorId from the updated object
        int authorId = updatedAuthor.getAuthorId(); 
        
        // Check if author exists
        if (authors.containsKey(authorId)) { 
            // Replace old Author object with updated one
            authors.put(authorId, updatedAuthor); 
            System.out.println("Author Updated");
        } else {
            System.out.println("Author with ID " + authorId + " not found!");
        }
    }
    
    // method to deleta a author by Id
    public void deleteAuthor(int authorId) {
        // Check if the author exists
        if (authors.containsKey(authorId)) { 
            authors.remove(authorId); 
            // Remove by ID
            System.out.println("Author with ID " + authorId + " has been deleted.");
        } else {
            System.out.println("Author with ID " + authorId + " not found.");
        }
    }
    
    // method to find out what is the next Id available
    public int getNextAuthorId(){
        if (authors.isEmpty()) {
            // Start with ID 201 if no authors exist
            return 201; 
        }
        
        // Start from 0 to find the highest existing ID
        int maxAuthorId = 0; 

        // Iterate through all keys in the HashMap
        for (int authorId : authors.keySet()) {
            if (authorId > maxAuthorId) {
                // Update max ID
                maxAuthorId = authorId; 
            }
        }
         // Return next available ID
        return maxAuthorId + 1;
    }
}
