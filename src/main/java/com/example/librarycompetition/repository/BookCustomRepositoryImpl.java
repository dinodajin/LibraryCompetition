package com.example.librarycompetition.repository;

import com.example.librarycompetition.domain.Book;
import com.example.librarycompetition.dto.BookDTO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class BookCustomRepositoryImpl implements BookCustomRepository {

    private final MongoTemplate mongoTemplate;

    public BookCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<BookDTO> findBooksByDynamicQuery(String bookTitle, String bookAuthor, Integer bookDamage) {
        Query query = new Query();

        if (bookTitle != null && !bookTitle.isEmpty()) {
            query.addCriteria(Criteria.where("bookTitle").regex(bookTitle, "i")); // 대소문자 구분없이 검색
        }

        if (bookAuthor != null && !bookAuthor.isEmpty()) {
            query.addCriteria(Criteria.where("bookAuthor").regex(bookAuthor, "i"));
        }

        if (bookDamage != null) {
            query.addCriteria(Criteria.where("bookDamage").is(bookDamage));
        }

        List<Book> bookList = mongoTemplate.find(query, Book.class);
        List<BookDTO> bookDTOs = new ArrayList<>();

        for (Book book : bookList) {
            bookDTOs.add(BookDTO.from(book));
        }

        return bookDTOs;
    }
}
