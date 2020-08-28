package com.human.userchat.impl;



import com.human.userchat.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepo {
    boolean  exits(String email) throws SQLException;
    User insert(User user) throws SQLException;
    Optional<User> findByEmailAndPassword(String email, String password) throws  SQLException;

    List<User> findAll() throws SQLException;

}
