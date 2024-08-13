package com.example.librarycompetition.repository;

import com.example.librarycompetition.domain.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {

    // READ
    Optional<Image> findByImageId(String imageId);
    List<Image> findImagesByBookId(String bookId);
    List<Image> findImagesByCameraId(Integer cameraId);
    List<Image> findImagesByImageTimeGreaterThan(LocalDate startDate);
    List<Image> findImagesByImageTimeBetween(LocalDate startDate, LocalDate endDate);

    // CREATE

    // UPDATE

    // DELETE
    void deleteById(String imageId);

}
