package com.database.controller.external;

import com.database.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/file",
        produces = "application/json")
public class FileController {
    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String uploadFile( @RequestParam("file") MultipartFile uploadedFile) throws IOException {
        return fileService.uploadFile(uploadedFile);
    }

//    @GetMapping
//    public ResponseEntity<>
}
