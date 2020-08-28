package com.human.userchat.servlet.mapper;

import com.human.userchat.model.User;
import com.human.userchat.model.dto.UserDto;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface UserMapper extends Mapper<User, UserDto>{

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
//    @Mapping(source = "id",target = "id")
//    @Mapping(source = "fullName",target = "name"+ " "+"surname")
//    @Mapping(source = "imageUrl",target = "imageURL")
//    List<UserDto> toUserDtoList(List<User> userList);

}