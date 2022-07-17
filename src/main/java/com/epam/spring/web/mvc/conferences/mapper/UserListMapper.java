package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserListMapper {
  UserListMapper INSTANCE = Mappers.getMapper(UserListMapper.class);

  List<User> toModelList(List<UserDto> dtos);

  List<UserDto> toDTOList(List<User> models);
}
