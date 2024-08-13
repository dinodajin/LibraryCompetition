package com.example.librarycompetition.repository;

import com.example.librarycompetition.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    // READ
    Optional<Book> findByBookId(String bookId);
    List<Book> findBooksByBookTitle(String bookTitle);
    List<Book> findBooksByBookAuthor(String bookAuthor);
    List<Book> findBooksByBookDamage(Integer bookDamage);
    List<Book> findBooksByBookLabel(String bookLabel);

    // CREATE

    // UPDATE

    // DELETE
    void deleteByBookId(String bookId);

}
