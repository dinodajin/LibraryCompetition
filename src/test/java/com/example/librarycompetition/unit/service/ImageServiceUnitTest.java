package com.example.librarycompetition.unit.service;

import com.example.librarycompetition.domain.Image;
import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.exception.ListNotFoundElementException;
import com.example.librarycompetition.exception.ResourceNotFoundException;
import com.example.librarycompetition.repository.ImageRepository;
import com.example.librarycompetition.service.ImageService;
import io.swagger.v3.oas.annotations.media.Schema;
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
        String imageId = "1";
        String imageUrl = "https://image.com";
        LocalDate imageDate = LocalDate.of(2024, 8, 21);
        Integer cameraId = 1;
        String bookId = "1";
        imageDTO = ImageDTO.of(imageId, imageUrl, imageDate, cameraId, bookId);
        image = imageDTO.toEntity();
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneImage 테스트")
        void testGetOneImage() {
            // given
            given(imageRepository.findByImageId(imageDTO.imageId())).willReturn(Optional.of(image));

            // when
            ImageDTO result = imageService.getOneImage(imageDTO.imageId());

            // then
            assertNotNull(result);
            assertEquals(imageDTO.imageId(), result.imageId());
        }

        @Test
        @DisplayName("getAllImage 테스트")
        void testGetAllImage() {
            // given
            List<Image> images = Collections.singletonList(image);
            given(imageRepository.findAll()).willReturn(images);

            // when
            List<ImageDTO> result = imageService.getAllImage();

            // then
            assertFalse(result.isEmpty());
            assertEquals(imageDTO.imageId(), result.get(0).imageId());
        }

        @Test
        @DisplayName("getImagesByBookId 테스트")
        void testGetImagesByBookId() {
            // given
            List<Image> images = Collections.singletonList(image);
            given(imageRepository.findImagesByBookId(imageDTO.bookId())).willReturn(images);

            // when
            List<ImageDTO> result = imageService.getImagesByBookId(imageDTO.bookId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(imageDTO.bookId(), result.get(0).bookId());
        }

        @Test
        @DisplayName("getImagesByCameraId 테스트")
        void testGetImagesByCameraId() {
            // given
            List<Image> images = Collections.singletonList(image);
            given(imageRepository.findImagesByCameraId(imageDTO.cameraId())).willReturn(images);

            // when
            List<ImageDTO> result = imageService.getImagesByCameraId(imageDTO.cameraId());

            // then
            assertFalse(result.isEmpty());
            assertEquals(imageDTO.cameraId(), result.get(0).cameraId());
        }

        @Test
        @DisplayName("getImagesByImageTimeGreaterThan 테스트")
        void testGetImagesByImageTimeGreaterThan() {
            // given
            List<Image> images = Collections.singletonList(image);
            given(imageRepository.findImagesByImageTimeGreaterThan(imageDTO.imageDate())).willReturn(images);

            // when
            List<ImageDTO> result = imageService.getImagesByImageTimeGreaterThan(imageDTO.imageDate());

            // then
            assertFalse(result.isEmpty());
            assertEquals(imageDTO.imageDate(), result.get(0).imageDate());
        }

        @Test
        @DisplayName("getImagesByImageTimeBetween 테스트")
        void testGetImagesByImageTimeBetween() {
            // given
            LocalDate endDate = LocalDate.now().plusDays(1);
            List<Image> images = Collections.singletonList(image);
            given(imageRepository.findImagesByImageTimeBetween(imageDTO.imageDate(), endDate)).willReturn(images);

            // when
            List<ImageDTO> result = imageService.getImagesByImageTimeBetween(imageDTO.imageDate(), endDate);

            // then
            assertFalse(result.isEmpty());
            assertEquals(imageDTO.imageDate(), result.get(0).imageDate());
        }

        @Test
        @DisplayName("getAllImage - ListNotFoundElementException 테스트")
        void testGetAllImage_ThrowsListNotFoundElementException() {
            // given
            given(imageRepository.findAll()).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> imageService.getAllImage());
        }

        @Test
        @DisplayName("getImagesByBookId - ListNotFoundElementException 테스트")
        void testGetImagesByBookId_ThrowsListNotFoundElementException() {
            // given
            given(imageRepository.findImagesByBookId(imageDTO.bookId())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> imageService.getImagesByBookId(imageDTO.bookId()));
        }

        @Test
        @DisplayName("getImagesByCameraId - ListNotFoundElementException 테스트")
        void testGetImagesByCameraId_ThrowsListNotFoundElementException() {
            // given
            given(imageRepository.findImagesByCameraId(imageDTO.cameraId())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> imageService.getImagesByCameraId(imageDTO.cameraId()));
        }

        @Test
        @DisplayName("getImagesByImageTimeGreaterThan - ListNotFoundElementException 테스트")
        void testGetImagesByImageTimeGreaterThan_ThrowsListNotFoundElementException() {
            // given
            given(imageRepository.findImagesByImageTimeGreaterThan(imageDTO.imageDate())).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> imageService.getImagesByImageTimeGreaterThan(imageDTO.imageDate()));
        }

        @Test
        @DisplayName("getImagesByImageTimeBetween - ListNotFoundElementException 테스트")
        void testGetImagesByImageTimeBetween_ThrowsListNotFoundElementException() {
            // given
            LocalDate endDate = LocalDate.now().plusDays(1);
            given(imageRepository.findImagesByImageTimeBetween(imageDTO.imageDate(), endDate)).willReturn(new ArrayList<>());

            // when & then
            assertThrows(ListNotFoundElementException.class, () -> imageService.getImagesByImageTimeBetween(imageDTO.imageDate(), endDate));
        }
    }

    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {

        @Test
        @DisplayName("createImage 테스트")
        void testCreateImage() {
            // given
            given(imageRepository.insert(image)).willReturn(image);

            // when
            ImageDTO result = imageService.createImage(imageDTO);

            // then
            assertNotNull(result);
            assertEquals(imageDTO.imageId(), result.imageId());
        }
    }

    @Nested
    @DisplayName("PUT 테스트")
    class Test_PUT {

        @Test
        @DisplayName("updateImage 테스트")
        void testUpdateImage() {
            // given
            given(imageRepository.save(image)).willReturn(image);

            // when
            ImageDTO result = imageService.updateImage(imageDTO);

            // then
            assertNotNull(result);
            assertEquals(imageDTO.imageId(), result.imageId());
        }
    }

    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {

        @Test
        @DisplayName("deleteImage 테스트")
        void testDeleteImage() {
            // given
            willDoNothing().given(imageRepository).deleteById(imageDTO.imageId());

            // when
            imageService.deleteImage(imageDTO.imageId());

            // then
            verify(imageRepository, times(1)).deleteById(imageDTO.imageId());
        }
    }
}