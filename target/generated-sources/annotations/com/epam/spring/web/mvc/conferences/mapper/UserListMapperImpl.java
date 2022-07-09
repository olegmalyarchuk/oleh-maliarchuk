package com.epam.spring.web.mvc.conferences.mapper;

import com.epam.spring.web.mvc.conferences.dto.UserDto;
import com.epam.spring.web.mvc.conferences.persistence.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-09T22:36:47+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserListMapperImpl implements UserListMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> toModelList(List<UserDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserDto userDto : dtos ) {
            list.add( userMapper.toModel( userDto ) );
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
            list.add( userMapper.toDTO( user ) );
        }

        return list;
    }
}
