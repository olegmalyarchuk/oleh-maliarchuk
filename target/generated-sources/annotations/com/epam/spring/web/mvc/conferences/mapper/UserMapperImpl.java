package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.dto.UserDto.UserDtoBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.persistence.model.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-03T01:03:03+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDTO(User model) {
        if ( model == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( model.getId() );
        userDto.role_id( model.getRole_id() );
        userDto.user_name( model.getUser_name() );
        userDto.user_surname( model.getUser_surname() );
        userDto.user_password( model.getUser_password() );
        userDto.user_phone( model.getUser_phone() );
        userDto.user_email( model.getUser_email() );
        userDto.user_photo_url( model.getUser_photo_url() );
        userDto.user_address( model.getUser_address() );

        return userDto.build();
    }

    @Override
    public User toModel(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( dto.getId() );
        user.role_id( dto.getRole_id() );
        user.user_name( dto.getUser_name() );
        user.user_surname( dto.getUser_surname() );
        user.user_password( dto.getUser_password() );
        user.user_phone( dto.getUser_phone() );
        user.user_email( dto.getUser_email() );
        user.user_photo_url( dto.getUser_photo_url() );
        user.user_address( dto.getUser_address() );

        return user.build();
    }
}
