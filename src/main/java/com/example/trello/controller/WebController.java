package com.example.trello.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class WebController {

  @GetMapping("boards/{boardId}/edit")
  public String editPage() {
    return "edit";
  }

  @GetMapping("boards/{boardId}")
  public String readOnlyPage() {
    return "readOnly";
  }

  @PostMapping("upload")
  public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
    if (file.isEmpty()) {
      model.addAttribute("message", "Please select a file to upload.");
      return "uploadResult";
    }
    try {
      String uploadDir = "C:/uploads/";
      String filename = file.getOriginalFilename();
      File dest = new File(uploadDir + filename);
      file.transferTo(dest);

      model.addAttribute("message", "File uploaded successfully: " + filename);
    } catch (IOException e) {
      model.addAttribute("message", "File upload failed: " + e.getMessage());
    }

    return "uploadResult";
  }

  @GetMapping("download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
    Path filePath = Paths.get("C:/uploads/").resolve(filename); // 파일 경로 설정
    try {
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.notFound().build();
  }
}