package com.musicstreaming.services;

import com.musicstreaming.dtos.RefreshTokenDTO;
import com.musicstreaming.dtos.UpdateUserDTO;
import com.musicstreaming.dtos.UserDTO;
import com.musicstreaming.exceptions.payload.DataNotFoundException;
import com.musicstreaming.models.User;
import com.musicstreaming.responses.user.LoginResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUser(UserDTO userDTO) throws Exception;

    String login(String phoneNumber, String password) throws Exception;

    User getUserDetailsFromToken(String token) throws Exception;

    User updateUser(Long userId, UpdateUserDTO updateUserDTO) throws Exception;

    LoginResponse refreshToken(RefreshTokenDTO refreshTokenDTO);

    Page<User> findAllUsers(String keyword, Pageable pageable);

    void resetPassword(Long userId, String newPassword) throws Exception;

    void blockOrEnable(Long userId, Boolean active) throws DataNotFoundException;
}
