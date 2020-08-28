package com.human.userchat.service;



import com.human.userchat.exception.DatabaseException;
import com.human.userchat.model.User;
import org.apache.commons.fileupload.FileUploadException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean userExist(String email) throws DatabaseException;
    User save(User user, InputStream imageContent) throws DatabaseException, FileUploadException;
    Optional<User> getUser(String emil, String password) throws  DatabaseException;
    List<User> getAll() throws DatabaseException;
}
