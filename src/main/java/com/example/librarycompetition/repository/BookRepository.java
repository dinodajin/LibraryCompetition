package com.example.librarycompetition.repository;

import com.example.librarycompetition.domain.Book;
import com.example.librarycompetition.repository.custom.BookCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String>, BookCustomRepository {

    // READ
    Optional<Book> findByBookId(String bookId);
    List<Book> findBooksByBookTitleContaining(String bookTitle);
    List<Book> findBooksByBookAuthorContaining(String bookAuthor);
    List<Book> findBooksByBookDamage(Integer bookDamage);
    List<Book> findBooksByBookLabel(String bookLabel);
    List<Book> findBooksByBookWarning(String bookWarning);

    // CREATE

    // UPDATE

    // DELETE
    void deleteByBookId(String bookId);

}
