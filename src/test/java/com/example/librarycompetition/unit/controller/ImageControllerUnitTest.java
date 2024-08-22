package com.example.librarycompetition.unit.controller;

import com.example.librarycompetition.controller.ImageController;
import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ImageControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    private ImageDTO imageDTO;

    @BeforeEach
    @DisplayName("스텁 설정")
    void setUp() {
        // 테스트에 사용할 ImageDTO 객체를 초기화합니다.
        String imageId = "test";
        String bookId = "bookTest";
        Integer cameraId = 123;
        LocalDate imageTime = LocalDate.of(2023, 8, 21);

        imageDTO = ImageDTO.of(imageId, bookId, cameraId, imageTime);
    }

    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {

        @Test
        @DisplayName("getOneImage 테스트")
        void testGetOneImage() throws Exception {
            // given
            String imageId
        }

        @Test
        @DisplayName("getAllImage 테스트")
        void testGetAllImage() throws Exception {
            // given
            List<ImageDTO> imageList = Collections.singletonList(imageDTO);
            given(imageService.getAllImage()).willReturn(imageList);

            // when & then
            mockMvc.perform(get("/image/get/all")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].imageId").value(imageDTO.getImageId()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getImagesByBookId 테스트")
        void testGetImagesByBookId() throws Exception {
            // given
            String bookId = "bookTest";
            List<ImageDTO> imageList = Collections.singletonList(imageDTO);
            given(imageService.getImagesByBookId(bookId)).willReturn(imageList);

            // when & then
            mockMvc.perform(get("/image/get/bookId/" + bookId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].bookId").value(bookId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getImagesByCameraId 테스트")
        void testGetImagesByCameraId() throws Exception {
            // given
            Integer cameraId = 123;
            List<ImageDTO> imageList = Collections.singletonList(imageDTO);
            given(imageService.getImagesByCameraId(cameraId)).willReturn(imageList);

            // when & then
            mockMvc.perform(get("/image/get/cameraId/" + cameraId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].cameraId").value(cameraId))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getImagesByImageTimeGreaterThan 테스트")
        void testGetImagesByImageTimeGreaterThan() throws Exception {
            // given
            LocalDate startDate = LocalDate.of(2023, 1, 1);
            List<ImageDTO> imageList = Collections.singletonList(imageDTO);
            given(imageService.getImagesByImageTimeGreaterThan(startDate)).willReturn(imageList);

            // when & then
            mockMvc.perform(get("/image/get/startDate/" + startDate)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].imageTime").value(imageDTO.getImageTime().toString()))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("getImagesByImageTimeBetween 테스트")
        void testGetImagesByImageTimeBetween() throws Exception {
            // given
            LocalDate startDate = LocalDate.of(2023, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 12, 31);
            List<ImageDTO> imageList = Collections.singletonList(imageDTO);
            given(imageService.getImagesByImageTimeBetween(startDate, endDate)).willReturn(imageList);

            // when & then
            mockMvc.perform(get("/image/get/imageTime")
                            .param("startDate", startDate.toString())
                            .param("endDate", endDate.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].imageTime").value(imageDTO.getImageTime().toString()))
                    .andExpect(status().isOk());
        }
    }
}
