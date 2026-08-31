package com.platform.file;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;
    public FileController(FileService fileService){ this.fileService = fileService; }

    @PostMapping("/upload")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {
        String url = fileService.upload(file);
        return ResponseEntity.ok(Map.of("url", url));
    }
}