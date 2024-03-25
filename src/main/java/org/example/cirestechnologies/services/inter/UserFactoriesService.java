package org.example.cirestechnologies.services.inter;

import org.example.cirestechnologies.dto.UploadDtoRes;
import org.example.cirestechnologies.entities.DBUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface UserFactoriesService {
    List<DBUser> generateUsers(int count);
    UploadDtoRes saveJsonFileDateToDatabase(MultipartFile jsonFile) throws IOException;
}
