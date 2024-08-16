package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("")
    public ResponseEntity<ImageDTO> getOneImage(String imageId) {
        return new ResponseEntity<>(imageService.getOneImage(imageId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ImageDTO>> getAllImage() {
        return new ResponseEntity<>(imageService.getAllImage(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ImageDTO>> getImagesByBookId(String bookId) {
        return new ResponseEntity<>(imageService.getImagesByBookId(bookId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ImageDTO>> getImagesByCameraId(Integer cameraId) {
        return new ResponseEntity<>(imageService.getImagesByCameraId(cameraId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeGreaterThan(LocalDate startDate) {
        return new ResponseEntity<>(imageService.getImagesByImageTimeGreaterThan(startDate), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeBetween(LocalDate startDate, LocalDate endDate) {
        return new ResponseEntity<>(imageService.getImagesByImageTimeBetween(startDate, endDate), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ImageDTO> createImage(ImageDTO imageDTO) {
        return new ResponseEntity<>(imageService.createImage(imageDTO), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<ImageDTO> updateImage(ImageDTO imageDTO) {
        return new ResponseEntity<>(imageService.updateImage(imageDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteImage(String imageId) {
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
