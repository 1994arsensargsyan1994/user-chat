package com.human.userchat.impl;


import com.human.userchat.model.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessagesRepo {

  Message insert(Message message) throws SQLException;

  List<Message> fetchAll(int senderId, int receiverId) throws SQLException;

}
