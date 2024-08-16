package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/get/{imageId}")
    public ResponseEntity<ImageDTO> getOneImage(@PathVariable String imageId) {
        log.info("getOneImage: imageId = {}", imageId);
        return new ResponseEntity<>(imageService.getOneImage(imageId), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ImageDTO>> getAllImage() {
        log.info("getAllImage");
        return new ResponseEntity<>(imageService.getAllImage(), HttpStatus.OK);
    }

    @GetMapping("/get/bookId/{bookId}")
    public ResponseEntity<List<ImageDTO>> getImagesByBookId(@PathVariable String bookId) {
        log.info("getImagesByBookId: bookId = {}", bookId);
        return new ResponseEntity<>(imageService.getImagesByBookId(bookId), HttpStatus.OK);
    }

    @GetMapping("/get/cameraId/{cameraId}")
    public ResponseEntity<List<ImageDTO>> getImagesByCameraId(@PathVariable Integer cameraId) {
        log.info("getImagesByCameraId: cameraId = {}", cameraId);
        return new ResponseEntity<>(imageService.getImagesByCameraId(cameraId), HttpStatus.OK);
    }

    @GetMapping("/get/startDate/{startDate}")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeGreaterThan(@PathVariable LocalDate startDate) {
        log.info("getImagesByImageTimeGreaterThan: startDate = {}", startDate);
        return new ResponseEntity<>(imageService.getImagesByImageTimeGreaterThan(startDate), HttpStatus.OK);
    }

    @GetMapping("/get/imageTime")
    public ResponseEntity<List<ImageDTO>> getImagesByImageTimeBetween(@RequestParam LocalDate startDate,
                                                                      @RequestParam LocalDate endDate) {
        log.info("getImagesByImageTimeBetween: startDate = {}, endDate = {}", startDate, endDate);
        return new ResponseEntity<>(imageService.getImagesByImageTimeBetween(startDate, endDate), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ImageDTO> createImage(@RequestBody ImageDTO imageDTO) {
        log.info("createImage: imageDTO = {}", imageDTO);
        return new ResponseEntity<>(imageService.createImage(imageDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ImageDTO> updateImage(@RequestBody ImageDTO imageDTO) {
        log.info("updateImage: imageDTO = {}", imageDTO);
        return new ResponseEntity<>(imageService.updateImage(imageDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        log.info("deleteImage: imageId = {}", imageId);
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
