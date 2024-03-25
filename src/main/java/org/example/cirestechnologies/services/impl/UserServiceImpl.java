package org.example.cirestechnologies.services.impl;

import lombok.AllArgsConstructor;
import org.example.cirestechnologies.configs.FakeUserGenerator;
import org.example.cirestechnologies.dto.UploadDtoRes;
import org.example.cirestechnologies.entities.DBUser;
import org.example.cirestechnologies.services.inter.UserServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    private final FakeUserGenerator fakeUserGenerator;

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
        return null;
    }
}
