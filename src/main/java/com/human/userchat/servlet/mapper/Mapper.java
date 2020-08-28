package com.human.userchat.servlet.mapper;

import java.util.List;

interface Mapper<Entity, Dto> {
    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);

    List<Entity> toEntityList(List<Dto> dtoList);

    List<Dto> toDtoList(List<Entity> entityList);


}
