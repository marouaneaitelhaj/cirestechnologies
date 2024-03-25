package org.example.testcirestechnologies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.testcirestechnologies.dto.UploadDtoRes;
import org.example.testcirestechnologies.entity.DBUser;
import org.example.testcirestechnologies.service.inter.JsonFileDateSaverService;
import org.example.testcirestechnologies.service.inter.UsersGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UsersGeneratorService usersGeneratorService;

    private final JsonFileDateSaverService jsonFileDateSaverService;

    @GetMapping("/generate")
    public ResponseEntity<Object> generate(@RequestParam int count) throws IOException {
        List<DBUser> users = usersGeneratorService.generateUsers(count);

        String jsonContent = new ObjectMapper().writeValueAsString(users);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "users.json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(jsonContent);
    }


    @PostMapping(value = "/batch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadDtoRes> uploadUsers(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(jsonFileDateSaverService.saveJsonFileDateToDatabase(file));
    }

}