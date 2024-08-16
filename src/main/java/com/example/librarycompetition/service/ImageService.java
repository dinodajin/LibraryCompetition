package com.example.librarycompetition.service;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public ImageDTO getOneImage(String imageId) {
    }

    @Transactional
    public List<ImageDTO> getAllImage() {
        return null;
    }

    @Transactional
    public List<ImageDTO> getImagesByBookId(String bookId) {
    }

    @Transactional
    public List<ImageDTO> getImagesByCameraId(Integer cameraId) {
    }

    @Transactional
    public List<ImageDTO> getImagesByImageTimeGreaterThan(LocalDate startDate) {
    }

    @Transactional
    public List<ImageDTO> getImagesByImageTimeBetween(LocalDate startDate, LocalDate endDate) {
    }

    @Transactional
    public ImageDTO createImage(ImageDTO imageDTO) {
    }

    @Transactional
    public ImageDTO updateImage(ImageDTO imageDTO) {
    }

    @Transactional
    public Void deleteImage(String imageId) {
        return null;
    }

}
