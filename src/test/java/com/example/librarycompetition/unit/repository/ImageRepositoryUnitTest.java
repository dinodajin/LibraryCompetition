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
        image.setImageId("1");
        image.setImageUrl("sdfksdfjsdnfklsdnkl");
        image.setImageTime(LocalDate.of(2024, 8, 21));
        image.setCameraId(1);
        image.setBookId("1");
        imageRepository.save(image);
    }

    @Nested
    @DisplayName("READ 테스트")
    class Test_READ {

        @Test
        @DisplayName("findByImageId 테스트")
        void testFindByImageId() {
            // when
            Optional<Image> result = imageRepository.findByImageId(image.getImageId());

            // then
            assertTrue(result.isPresent());
            assertEquals(image.getImageId(), result.get().getImageId());
        }

        @Test
        @DisplayName("findImagesByBookId 테스트")
        void testFindImagesByBookId() {
            // when
            List<Image> result = imageRepository.findImagesByBookId(image.getBookId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(image.getBookId(), result.get(0).getBookId());
        }

        @Test
        @DisplayName("findImagesByCameraId 테스트")
        void testFindImagesByCameraId() {
            // when
            List<Image> result = imageRepository.findImagesByCameraId(image.getCameraId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(image.getCameraId(), result.get(0).getCameraId());
        }

        @Test
        @DisplayName("findImagesByImageTimeGreaterThan 테스트")
        void testFindImagesByImageTimeGreaterThan() {
            // when
            List<Image> result = imageRepository.findImagesByImageTimeGreaterThan(LocalDate.of(2024, 8, 20));

            // then
            assertFalse(result.isEmpty());
            assertTrue(result.stream().allMatch(img -> img.getImageTime().isAfter(LocalDate.of(2024, 8, 20))));
        }

        @Test
        @DisplayName("findImagesByImageTimeBetween 테스트")
        void testFindImagesByImageTimeBetween() {
            // when
            List<Image> result = imageRepository.findImagesByImageTimeBetween(LocalDate.of(2024, 8, 20), LocalDate.of(2024, 8, 22));

            // then
            assertFalse(result.isEmpty());
            assertTrue(result.stream().allMatch(img -> !img.getImageTime().isBefore(LocalDate.of(2024, 8, 20)) && !img.getImageTime().isAfter(LocalDate.of(2024, 8, 22))));
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteById 테스트")
        void testDeleteById() {
            // when
            imageRepository.deleteById(image.getImageId());

            // then
            Optional<Image> result = imageRepository.findByImageId(image.getImageId());
            assertFalse(result.isPresent());
        }
    }
}