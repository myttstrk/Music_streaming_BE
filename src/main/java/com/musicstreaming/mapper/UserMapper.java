package com.musicstreaming.mapper;

import com.musicstreaming.dtos.UserDTO;
import com.musicstreaming.models.User;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")

public interface UserMapper {
    User toUser(UserDTO userDTO);
}
