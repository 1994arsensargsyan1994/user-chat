package com.human.userchat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class Message {

  private int id;

  @JsonProperty("sender_id")
  private int senderId;

  @JsonProperty("receiver_id")
  private int receiverId;
  private String message;

  @JsonProperty("created_at")
  private Date createdAt;

}
