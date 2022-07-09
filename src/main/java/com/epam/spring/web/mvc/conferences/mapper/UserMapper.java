package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDTO(User model);
    User toModel(UserDto dto);
}
