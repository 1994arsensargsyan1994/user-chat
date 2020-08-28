package com.human.userchat.service;



import com.human.userchat.exception.DatabaseException;
import com.human.userchat.impl.MessagesRepo;
import com.human.userchat.impl.MessagesRepoSql;
import com.human.userchat.model.Message;

import java.sql.SQLException;
import java.util.List;

public class MessagesServiceImpl implements MessagesService {

  private MessagesRepo messagesDao;

  public MessagesServiceImpl() {
    this.messagesDao = new MessagesRepoSql();
  }

  @Override
  public Message add(Message message) throws DatabaseException {
    try {
      return this.messagesDao.insert(message);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public List<Message> getAllMessages(int senderId, int receiverId) throws DatabaseException {
    try {
      return this.messagesDao.fetchAll(senderId, receiverId);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }
}
