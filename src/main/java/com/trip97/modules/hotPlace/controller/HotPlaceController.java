package com.trip97.modules.hotPlace.controller;

import com.trip97.modules.hotPlace.model.FileInfoDto;
import com.trip97.modules.hotPlace.model.HotPlace;
import com.trip97.modules.hotPlace.model.HotPlaceListDto;
import com.trip97.modules.hotPlace.model.service.HotPlaceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/hotplace")
@RequiredArgsConstructor
public class HotPlaceController {

    private final HotPlaceService hotPlaceService;

    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.path-upload-images}")
    private String uploadImagePath;

    @GetMapping
    public ResponseEntity<?> getHotPlaces(@RequestParam Map<String, String> map) {
        HotPlaceListDto hotPlaceList = hotPlaceService.getHotPlaces(map);
        if (hotPlaceList != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(hotPlaceList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHotPlaceById(@PathVariable("id") Integer id) {
        HotPlace hotPlace = hotPlaceService.getHotPlace(id);
        if (hotPlace != null) {
            hotPlaceService.updateHit(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(headers).body(hotPlace);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> registerHotPlace(@RequestPart("hotPlace") HotPlace hotPlace, @RequestPart("upfile") MultipartFile[] files, HttpServletRequest request) throws IOException {
        String contentType = request.getContentType();
        log.info("Received Content-Type: {}", contentType);
        //  FileUpload 관련 설정.
        log.info("uploadPath : {}", uploadPath);
        log.info("MultipartFile.isEmpty : {}", files[0].isEmpty());
        if (!files[0].isEmpty()) {
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            String saveFolder = uploadPath + File.separator + today;
            log.info("저장 폴더 : {}", saveFolder);
            File folder = new File(saveFolder);
            if (!folder.exists())
                folder.mkdirs();
            List<FileInfoDto> fileInfos = new ArrayList<>();
            for (MultipartFile mfile : files) {
                FileInfoDto fileInfoDto = new FileInfoDto();
                String originalFileName = mfile.getOriginalFilename();
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString()
                            + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    fileInfoDto.setSaveFolder(today);
                    fileInfoDto.setOriginalFile(originalFileName);
                    fileInfoDto.setSaveFile(saveFileName);
                    log.info("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
                    mfile.transferTo(new File(folder, saveFileName));
                }
                fileInfos.add(fileInfoDto);
            }
            hotPlace.setFileInfos(fileInfos);
        }
        hotPlaceService.registerHotPlace(hotPlace);
        HotPlace createdHotPlace = hotPlaceService.getHotPlace(hotPlace.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(createdHotPlace);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateHotPlace(@PathVariable int id, @RequestBody HotPlace hotPlace) {
        hotPlaceService.editHotPlace(hotPlace);
        HotPlace updatedHotPlace = hotPlaceService.getHotPlace(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(updatedHotPlace);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeHotPlace(@PathVariable int id) {
        hotPlaceService.removeHotPlace(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/like")
    public ResponseEntity<?> checkLike(@RequestParam int memberId, @RequestParam int hotPlaceId) {
        log.info("checkLike 컨트롤러 호출!");
        return ResponseEntity.ok(hotPlaceService.isLiked(memberId, hotPlaceId));
    }

    @PutMapping("/api/like")
    public ResponseEntity<?> updateLike(@RequestBody Map<String, Integer> params) {
        int memberId = params.get("memberId");
        int hotPlaceId = params.get("hotPlaceId");
        hotPlaceService.updateLike(memberId, hotPlaceId);
        return ResponseEntity.noContent().build();
    }
}
