package com.human.userchat.servlet.users;



import com.human.userchat.model.Message;
import com.human.userchat.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet({"/home",""})
public class HomeServlet extends BaseServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    try {
      User user = (User) req.getSession().getAttribute("user");
      List<User> users = this.userService.getAll();
      List<Message> messages = this.messagesService.getAllMessages(user.getId(), user.getId());
      req.setAttribute("users", users);
      req.setAttribute("messages", messages);
      req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
