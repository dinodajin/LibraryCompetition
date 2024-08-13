package com.example.librarycompetition.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collation = "image")
public class Image {

    @Id
    private String imageId;

    private String imageUrl;
    private LocalDate imageTime;
    private Integer cameraId;

    private String bookId;

}
