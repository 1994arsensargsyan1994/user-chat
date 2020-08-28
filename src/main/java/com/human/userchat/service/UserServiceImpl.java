package com.human.userchat.service;


import com.human.userchat.exception.DatabaseException;
import com.human.userchat.model.User;
import com.human.userchat.impl.UserRepo;
import com.human.userchat.impl.UserRepoSql;
import com.human.userchat.util.Settings;
import org.apache.commons.fileupload.FileUploadException;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl() {
        this.userRepo = new UserRepoSql();
    }


    @Override
    public boolean userExist(String email) throws DatabaseException {
        try {
            return userRepo.exits(email);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User save(User user, InputStream imageContent) throws DatabaseException, FileUploadException {
            String imageName = UUID.nameUUIDFromBytes(user.getEmail().getBytes()).toString();
      //   String path = UserServiceImpl.class.getClassLoader().getResource("../../images").getFile() + imageName;
        String path = Settings.getInstance().getString("images.path") + imageName;
        try {
            if (imageContent != null) {
                try (OutputStream out = new FileOutputStream(path)) {
                    byte[] buffer = new byte[2048];
                    int readCount;
                    while ((readCount = imageContent.read(buffer)) > -1) {
                        out.write(buffer, 0, readCount);
                    }
                    user.setImageURL("/images/" + imageName);
                } catch (IOException e) {
                    throw new FileUploadException(e.getMessage());
                }
            } else {
                user.setImageURL("/images/incognito.png");
            }
            user = this.userRepo.insert(user);
            return user;
        } catch (SQLException e) {
            new File(path).delete();
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<User> getUser(String emil, String password) throws DatabaseException {
        try {
            return this.userRepo.findByEmailAndPassword(emil, password);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<User> getAll() throws DatabaseException {
        try {
            return this.userRepo.findAll();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

}
