package com.example.librarycompetition.controller;

import com.example.librarycompetition.dto.ImageDTO;
import com.example.librarycompetition.service.ImageForwardingService;
import com.example.librarycompetition.service.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Tag(name = "imageForwarding", description = "Image Forwarding API")
@Slf4j
@RestController
@RequestMapping("/imageForwarding")
@RequiredArgsConstructor
public class ImageForwardingController {

    private final ImageForwardingService imageForwardingService;
    private final ImageService imageService;

    @PostMapping(path = "/post", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String, Object>> postImageForwarding(@RequestPart("images") List<MultipartFile> images,
                                                                   @RequestPart("metadata") List<ImageDTO> metadata) throws IOException, InterruptedException {
        List<String> urls = imageForwardingService.saveImage(images);

        IntStream.range(0, metadata.size()).forEach(i -> {
            ImageDTO imageDTO = metadata.get(i);
            String url = urls.get(i);

            imageService.createImage(ImageDTO.of(imageDTO.imageId(), url, imageDTO.imageTime(), imageDTO.cameraId(), imageDTO.bookId()));
        });

        return new ResponseEntity<>(imageForwardingService.processingImage(), HttpStatus.OK);
    }
}
