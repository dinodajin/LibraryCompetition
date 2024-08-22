package com.example.librarycompetition.unit.service;

import com.example.librarycompetition.domain.Image;
import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.ImageRepository;
import com.example.librarycompetition.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class ImageServiceUnitTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageService imageService;

    private ImageDTO imageDTO;
    private Image image;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String imageId = "testImageId";
        Integer cameraId = 1;
        String bookId = "testBookId";
        LocalDate imageTime = LocalDate.now();

        imageDTO = ImageDTO.of(imageId, cameraId, bookId, imageTime);
        image = imageDTO.toEntity();
    }
}