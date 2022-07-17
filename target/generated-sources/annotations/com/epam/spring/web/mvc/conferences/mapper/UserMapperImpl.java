package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.dto.UserDto.UserDtoBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.persistence.model.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T16:15:17+0300",
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
        userDto.roleId( model.getRoleId() );
        userDto.userName( model.getUserName() );
        userDto.userSurname( model.getUserSurname() );
        userDto.userPassword( model.getUserPassword() );
        userDto.userPhone( model.getUserPhone() );
        userDto.userEmail( model.getUserEmail() );
        userDto.userPhotoUrl( model.getUserPhotoUrl() );
        userDto.userAddress( model.getUserAddress() );

        return userDto.build();
    }

    @Override
    public User toModel(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( dto.getId() );
        user.roleId( dto.getRoleId() );
        user.userName( dto.getUserName() );
        user.userSurname( dto.getUserSurname() );
        user.userPassword( dto.getUserPassword() );
        user.userPhone( dto.getUserPhone() );
        user.userEmail( dto.getUserEmail() );
        user.userPhotoUrl( dto.getUserPhotoUrl() );
        user.userAddress( dto.getUserAddress() );

        return user.build();
    }
}
