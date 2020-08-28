package com.human.userchat.servlet.messages;



import com.human.userchat.service.MessagesService;
import com.human.userchat.service.MessagesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseMessagesServlet extends HttpServlet {

  protected MessagesService messagesService;

  @Override
  public void init() throws ServletException {
    super.init();
    this.messagesService = new MessagesServiceImpl();
  }
}
