package com.capstone.hanperform.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PosterService {

    private final PosterRepository posterRepository;

    @Transactional
    public Poster uploadThumbnail(MultipartFile image) {

        Poster poster = null;

        try {
            String uploadsDir = "upload/images/";
            String dbFilePath = saveImage(image, uploadsDir);

            poster = new Poster(dbFilePath);
            posterRepository.save(poster);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return poster;
    }

    // 이미지 파일을 저장하는 메서드
    private String saveImage(MultipartFile image, String uploadsDir) throws IOException {
        // 파일 이름 생성
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + image.getOriginalFilename();
        // 실제 파일이 저장될 경로
        String filePath = uploadsDir + fileName;
        // DB에 저장할 경로 문자열
        String dbFilePath = "images/" + fileName;

        Path path = Paths.get(filePath); // Path 객체 생성
        Files.createDirectories(path.getParent()); // 디렉토리 생성
        Files.write(path, image.getBytes()); // 디렉토리에 파일 저장

        return dbFilePath;
    }
}
