package com.example.librarycompetition.service;

import com.example.librarycompetition.domain.Image;
import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public ImageDTO getOneImage(String imageId) {
        return ImageDTO.from(imageRepository.findByImageId(imageId).orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional
    public List<ImageDTO> getAllImage() {
        List<Image> images = imageRepository.findAll();

        if (images.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (Image image : images) {
            imageDTOs.add(ImageDTO.from(image));
        }

        return imageDTOs;
    }

    @Transactional
    public List<ImageDTO> getImagesByBookId(String bookId) {
        List<Image> images = imageRepository.findImagesByBookId(bookId);

        if (images.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (Image image : images) {
            imageDTOs.add(ImageDTO.from(image));
        }

        return imageDTOs;
    }

    @Transactional
    public List<ImageDTO> getImagesByCameraId(Integer cameraId) {
        List<Image> images = imageRepository.findImagesByCameraId(cameraId);

        if (images.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (Image image : images) {
            imageDTOs.add(ImageDTO.from(image));
        }

        return imageDTOs;

    }

    @Transactional
    public List<ImageDTO> getImagesByImageTimeGreaterThan(LocalDate startDate) {
        List<Image> images = imageRepository.findImagesByImageTimeGreaterThan(startDate);

        if (images.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (Image image : images) {
            imageDTOs.add(ImageDTO.from(image));
        }

        return imageDTOs;
    }

    @Transactional
    public List<ImageDTO> getImagesByImageTimeBetween(LocalDate startDate, LocalDate endDate) {
        List<Image> images = imageRepository.findImagesByImageTimeBetween(startDate, endDate);

        if (images.isEmpty()) {
            throw new ListNotFoundElementException();
        }

        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (Image image : images) {
            imageDTOs.add(ImageDTO.from(image));
        }

        return imageDTOs;
    }

    @Transactional
    public ImageDTO createImage(ImageDTO imageDTO) {
        return ImageDTO.from(imageRepository.insert(imageDTO.toEntity()));
    }

    @Transactional
    public ImageDTO updateImage(ImageDTO imageDTO) {
        return ImageDTO.from(imageRepository.save(imageDTO.toEntity()));
    }

    @Transactional
    public void deleteImage(String imageId) {
        imageRepository.deleteById(imageId);
    }

}
