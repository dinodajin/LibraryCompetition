package com.example.librarycompetition.unit.repository;

import com.example.librarycompetition.domain.Image;
import com.example.librarycompetition.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class ImageRepositoryUnitTest {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Image image;

    @BeforeEach
    @DisplayName("테스트 데이터 준비")
    void setUp() {
        mongoTemplate.dropCollection(Image.class);

        image = new Image();
        image.setImageId("testImageId");
        image.setBookId("testBookId");
        image.setCameraId(123);
        image.setImageTime(LocalDate.of(2024, 8, 21));

        imageRepository.save(image);
    }
}