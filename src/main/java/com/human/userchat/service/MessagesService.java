package com.human.userchat.service;



import com.human.userchat.exception.DatabaseException;
import com.human.userchat.model.Message;

import java.util.List;

public interface MessagesService {

  Message add(Message message) throws DatabaseException;

  List<Message> getAllMessages(int senderId, int receiverId) throws DatabaseException;
}
