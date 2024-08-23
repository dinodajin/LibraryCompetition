package com.example.librarycompetition.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

@Slf4j
@Service
public class ImageForwardingService {

    private final String DEFAULT_PATH = "C:/Users/trainee109/IdeaProjects/LibraryCompetition/src/main/java/com/example/librarycompetition/watch/";

    public List<String> saveImage(List<MultipartFile> images) throws IOException {
        List<String> urls = new ArrayList<>();

        for (MultipartFile image : images) {
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(DEFAULT_PATH + fileName);

            image.transferTo(filePath.toFile());

            String url = filePath.toString();
            urls.add(url);
        }
        return urls;
    }

    public Map<String, Object> processingImage() throws IOException, InterruptedException {
        // 감시할 디렉토리 경로 설정
        Path path = Paths.get(DEFAULT_PATH);
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // 디렉터리를 감시 대상으로 등록 (파일 생성 이벤트 감지)
        path.register(watchService, ENTRY_CREATE);

        List<Path> processedImages = new ArrayList<>();
        List<Path> jsonFiles = new ArrayList<>();

        // 원하는 파일 6개가 생성될 때까지 감시 (원본 이미지 3개, 전처리된 이미지 3개)
        int expectedFilesCount = 6;
        int filesDetected = 0;

        while (filesDetected < expectedFilesCount) {
            // WatchKey 대기 (파일 생성 이벤트가 발생할 때까지)
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == ENTRY_CREATE) {
                    // 생성된 파일의 이름 가져오기
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    Path filePath = path.resolve(fileName);

                    log.info("파일 생성 감지: {}", filePath);

                    // 파일 확장자에 따라 처리
                    if (fileName.toString().endsWith(".png")) {
                        processedImages.add(filePath);
                    } else if (fileName.toString().endsWith(".json")) {
                        jsonFiles.add(filePath);
                    }

                    filesDetected++;
                }
            }

            // 감시 작업 완료 후 키 리셋
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }

        // WatchService 닫기
        watchService.close();

        // 결과를 Map으로 반환
        Map<String, Object> result = new HashMap<>();
        result.put("processedImages", processedImages);
        result.put("jsonFiles", jsonFiles);

        return result;
    }
}
