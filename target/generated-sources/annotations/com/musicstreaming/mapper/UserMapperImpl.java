package com.musicstreaming.mapper;

import com.musicstreaming.dtos.UserDTO;
import com.musicstreaming.models.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-19T22:18:40+0700",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.fullname( userDTO.getFullname() );
        user.phoneNumber( userDTO.getPhoneNumber() );
        user.address( userDTO.getAddress() );
        user.password( userDTO.getPassword() );
        user.createdAt( userDTO.getCreatedAt() );
        user.updatedAt( userDTO.getUpdatedAt() );
        user.dateOfBirth( userDTO.getDateOfBirth() );
        user.facebookAccountId( userDTO.getFacebookAccountId() );
        user.googleAccountId( userDTO.getGoogleAccountId() );

        return user.build();
    }
}
