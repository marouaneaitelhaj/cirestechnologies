package org.example.cirestechnologies.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.cirestechnologies.configs.FakeUserGenerator;
import org.example.cirestechnologies.dto.UploadDtoRes;
import org.example.cirestechnologies.entities.DBUser;
import org.example.cirestechnologies.repositories.UserRepository;
import org.example.cirestechnologies.services.inter.UserServiceInterface;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    private final FakeUserGenerator fakeUserGenerator;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public List<DBUser> generateUsers(int count) {
        List<DBUser> users = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            DBUser user = fakeUserGenerator.generate(new DBUser());
            users.add(user);
        }
        return users;
    }

    @Override
    public UploadDtoRes saveJsonFileDateToDatabase(MultipartFile jsonFile) throws IOException {
        if (!Objects.equals(jsonFile.getContentType(), "application/json")) {
            throw new BadRequestException("Must be a JSON format");
        }

        List<DBUser> users = new ObjectMapper().readValue(jsonFile.getInputStream(), new TypeReference<List<DBUser>>() {
        });

        List<DBUser> savedUsers = new ArrayList<>();
        List<DBUser> failedUsers = new ArrayList<>();
        for (DBUser user : users) {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                savedUsers.add(userRepository.save(user));
            } catch (Exception e) {
                failedUsers.add(user);
            }
        }

        UploadDtoRes response = new UploadDtoRes();
        response.setTotalRecords(users.size());
        response.setImportedRecords(savedUsers.size());
        response.setFailedRecords(failedUsers.size());
        return response;
    }
}
