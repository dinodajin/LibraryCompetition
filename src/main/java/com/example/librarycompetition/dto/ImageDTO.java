package com.example.librarycompetition.dto;

import com.example.librarycompetition.domain.Image;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


public record ImageDTO(
        String imageId,
        String imageUrl,
        LocalDate imageDate,
        Integer cameraId,
        String bookId
) {

    public static ImageDTO of(String imageId, String imageUrl, LocalDate imageDate, Integer cameraId, String bookId) {
        return new ImageDTO(imageId, imageUrl, imageDate,  cameraId, bookId);
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
                .imageTime(imageDate)
                .cameraId(cameraId)
                .bookId(bookId)
                .build();
    }

}
