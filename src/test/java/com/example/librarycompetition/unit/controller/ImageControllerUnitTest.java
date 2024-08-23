package com.example.librarycompetition.unit.controller;
import com.example.librarycompetition.controller.ImageController;
import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.dto.LoanDTO;
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
        String imageId = "1";
        String imageUrl = "https://image.com";
        LocalDate imageTime = LocalDate.of(2024, 8, 21);
        Integer cameraId = 1;
        String bookId = "1";
        imageDTO = ImageDTO.of(imageId, imageUrl, imageTime, cameraId, bookId);
    }
    @Nested
    @DisplayName("GET 테스트")
    class Test_GET {
        @Test
        @DisplayName("getOneImage 테스트")
        void testGetOneImage() throws Exception {
            // given
            String imageId = "1";
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
                    .andExpect(jsonPath("$[0].imageId").value(imageDTO.imageId()))
                    .andExpect(status().isOk());
        }
        @Test
        @DisplayName("getImagesByBookId 테스트")
        void testGetImagesByBookId() throws Exception {
            // given
            String bookId = "1";
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
            Integer cameraId = 1;
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
        void testGetImagesByimageTimeGreaterThan() throws Exception {
            // given
            LocalDate startDate = LocalDate.of(2024, 8, 21);
            List<ImageDTO> imageList = Collections.singletonList(imageDTO);
            given(imageService.getImagesByImageTimeGreaterThan(startDate)).willReturn(imageList);
            // when & then
            mockMvc.perform(get("/image/get/startDate/" + startDate)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].imageTime").value(imageDTO.imageTime().toString()))
                    .andExpect(status().isOk());
        }
        @Test
        @DisplayName("getImagesByImageTimeBetween 테스트")
        void testGetImagesByimageTimeBetween() throws Exception {
            // given
            LocalDate startDate = LocalDate.of(2024, 1, 1);
            LocalDate endDate = LocalDate.of(2024, 12, 31);
            List<ImageDTO> imageList = Collections.singletonList(imageDTO);
            given(imageService.getImagesByImageTimeBetween(startDate, endDate)).willReturn(imageList);
            // when & then
            mockMvc.perform(get("/image/get/imageTime")
                            .param("startDate", startDate.toString())
                            .param("endDate", endDate.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].imageTime").value(imageDTO.imageTime().toString())) // 날짜 문자열 비교
                    .andExpect(status().isOk());
        }
    }
    @Nested
    @DisplayName("POST 테스트")
    class Test_POST {
        @Test
        @DisplayName("createImage 테스트")
        void testCreateImage() throws Exception {
            // given
            given(imageService.createImage(imageDTO)).willReturn(imageDTO);
            // when & then
            mockMvc.perform(post("/image/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"imageId\": \"1\", \"imageUrl\": \"https://image.com\", \"imageTime\": \"2024-08-21\", \"cameraId\": 1,\"bookId\": \"1\"}"))
                    .andExpect(jsonPath("$.imageId").value(imageDTO.imageId()))
                    .andExpect(status().isCreated());
        }
    }
    @Nested
    @DisplayName("PUT 테스트")
    class Test_PUT {
        @Test
        @DisplayName("updateImage 테스트")
        void testUpdateImage() throws Exception {
            // given
            given(imageService.updateImage(imageDTO)).willReturn(imageDTO);
            // when & then
            mockMvc.perform(put("/image/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"imageId\": \"1\", \"imageUrl\": \"https://image.com\", \"imageTime\": \"2024-08-21\", \"cameraId\": 1,\"bookId\": \"1\"}"))
                    .andExpect(jsonPath("$.imageId").value(imageDTO.imageId()))
                    .andExpect(status().isAccepted());
        }
    }
    @Nested
    @DisplayName("DELETE 테스트")
    class Test_DELETE {
        @Test
        @DisplayName("deleteImage 테스트")
        void testDeleteImage() throws Exception {
            // given
            String imageId = "test";
            doNothing().when(imageService).deleteImage(imageId);
            // when & then
            mockMvc.perform(delete("/image/delete/" + imageId)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent());
            then(imageService).should().deleteImage(imageId);
        }
    }
}