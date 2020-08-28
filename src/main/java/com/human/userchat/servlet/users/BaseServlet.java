package com.human.userchat.servlet.users;


import com.human.userchat.service.MessagesService;
import com.human.userchat.service.MessagesServiceImpl;
import com.human.userchat.service.UserService;
import com.human.userchat.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    protected UserService userService;
    protected MessagesService messagesService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl();
        this.messagesService = new MessagesServiceImpl();
    }
}
