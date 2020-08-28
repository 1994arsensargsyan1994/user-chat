package com.human.userchat.servlet.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.human.userchat.model.User;
import com.human.userchat.model.dto.UserDto;
import com.human.userchat.servlet.mapper.UserMapper;
import com.human.userchat.util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.*;


@WebServlet("/usersList")
public class UsersListServlet extends BaseServlet {

  private Map<Integer, Date> usersActivityMap;


  @Override
  @SuppressWarnings("unchecked")
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      if (usersActivityMap == null) {
        usersActivityMap = (Map<Integer, Date>) req.getServletContext().getAttribute("users_activity");
      }
      resp.setContentType("application/json");
      List<User> userList = super.userService.getAll();
      List<UserDto> userViews = UserMapper.MAPPER.toDtoList(userList);

      for (UserDto userDto : userViews) {

        Date activityDate = usersActivityMap.get(userDto.getId());
        userDto.setActive((activityDate != null &&
            System.currentTimeMillis() - activityDate.getTime() < 15 * 60 * 1000));
        userDto.setLastActiveTime(activityDate != null ? activityDate.getTime() : -1);

      }
      userViews.sort(Comparator.comparing(UserDto::getLastActiveTime).reversed());
      try (Writer writer = resp.getWriter()) {
        writer.write(JsonUtil.serialize(userViews));
      }
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

//  private static class UserView {
//    @JsonProperty("sender_id")
//    int id;
//    @JsonProperty("image_url")
//    String imageUrl;
//    @JsonProperty("full_name")
//    String fullName;
//    boolean active;
//
//    @JsonIgnore
//    long lastActiveTime;
//
//    public long getLastActiveTime() {
//      return lastActiveTime;
//    }
//
//    public void setLastActiveTime(long lastActiveTime) {
//      this.lastActiveTime = lastActiveTime;
//    }
//
//    public int getId() {
//      return id;
//    }
//
//    public void setId(int id) {
//      this.id = id;
//    }
//
//    public String getImageUrl() {
//      return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//      this.imageUrl = imageUrl;
//    }
//
//    public String getFullName() {
//      return fullName;
//    }
//
//    public void setFullName(String fullName) {
//      this.fullName = fullName;
//    }
//
//    public boolean isActive() {
//      return active;
//    }
//
//    public void setActive(boolean active) {
//      this.active = active;
//    }
//  }
}
