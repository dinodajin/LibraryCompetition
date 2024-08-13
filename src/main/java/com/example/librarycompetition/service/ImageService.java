package com.example.librarycompetition.service;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageDTO getOneImage(String imageId) {
    }

    public List<ImageDTO> getAllImage() {
        return null;
    }

    public List<ImageDTO> getImagesByBookId(String bookId) {
    }

    public List<ImageDTO> getImagesByCameraId(Integer cameraId) {
    }

    public List<ImageDTO> getImagesByImageTimeGreaterThan(LocalDate startDate) {
    }

    public List<ImageDTO> getImagesByImageTimeBetween(LocalDate startDate, LocalDate endDate) {
    }

    public ImageDTO createImage(ImageDTO imageDTO) {
    }

    public ImageDTO updateImage(ImageDTO imageDTO) {
    }

    public Void deleteImage(String imageId) {
        return null;
    }
}
