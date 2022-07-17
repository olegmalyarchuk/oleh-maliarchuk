package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.dto.UserDto.UserDtoBuilder;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import com.epam.spring.web.mvc.conferences.persistence.model.User.UserBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T16:15:17+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserListMapperImpl implements UserListMapper {

    @Override
    public List<User> toModelList(List<UserDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserDto userDto : dtos ) {
            list.add( userDtoToUser( userDto ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDTOList(List<User> models) {
        if ( models == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( models.size() );
        for ( User user : models ) {
            list.add( userToUserDto( user ) );
        }

        return list;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.roleId( userDto.getRoleId() );
        user.userName( userDto.getUserName() );
        user.userSurname( userDto.getUserSurname() );
        user.userPassword( userDto.getUserPassword() );
        user.userPhone( userDto.getUserPhone() );
        user.userEmail( userDto.getUserEmail() );
        user.userPhotoUrl( userDto.getUserPhotoUrl() );
        user.userAddress( userDto.getUserAddress() );

        return user.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.roleId( user.getRoleId() );
        userDto.userName( user.getUserName() );
        userDto.userSurname( user.getUserSurname() );
        userDto.userPassword( user.getUserPassword() );
        userDto.userPhone( user.getUserPhone() );
        userDto.userEmail( user.getUserEmail() );
        userDto.userPhotoUrl( user.getUserPhotoUrl() );
        userDto.userAddress( user.getUserAddress() );

        return userDto.build();
    }
}
