package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Image;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;


public record ImageDTO(
        @Schema(example = "1")
        String imageId,
        @Schema(example = "https://image.com")
        String imageUrl,
        @Schema(example = "2024-08-21")
        LocalDate imageTime,
        @Schema(example = "1")
        Integer cameraId,
        @Schema(example = "1")
        String bookId
) {

    public static ImageDTO of(String imageId, String imageUrl, LocalDate imageTime, Integer cameraId, String bookId) {
        return new ImageDTO(imageId, imageUrl, imageTime,  cameraId, bookId);
    }

    public static ImageDTO from(Image image) {
        return new ImageDTO(
                image.getImageId(),
                image.getImageUrl(),
                image.getImageTime(),
                image.getCameraId(),
                image.getBookId()
        );
    }

    public Image toEntity() {
        return Image.builder()
                .imageId(imageId)
                .imageUrl(imageUrl)
                .imageTime(imageTime)
                .cameraId(cameraId)
                .bookId(bookId)
                .build();
    }

}
