package com.musicstreaming.services.impl;

import com.musicstreaming.components.JwtTokenUtil;
import com.musicstreaming.dtos.RefreshTokenDTO;
import com.musicstreaming.dtos.UpdateUserDTO;
import com.musicstreaming.dtos.UserDTO;
import com.musicstreaming.exceptions.payload.DataNotFoundException;
//import com.musicstreaming.mapper.UserMapper;
import com.musicstreaming.mapper.UserMapper;
import com.musicstreaming.models.Role;
import com.musicstreaming.models.User;
import com.musicstreaming.repositories.RoleRepository;
import com.musicstreaming.repositories.TokenRepository;
import com.musicstreaming.repositories.UserRepository;
import com.musicstreaming.responses.user.LoginResponse;
import com.musicstreaming.services.UserService;
import com.musicstreaming.utils.MessageKeys;
import com.musicstreaming.utils.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final TokenServiceImpl refreshTokenService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String phoneNumber = userDTO.getPhoneNumber();
        // kiểm tra xem số điện thoại đã tồn tại hay chưa
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException(
                    MessageKeys.PHONE_NUMBER_EXISTED);
        }
        Role role = roleRepository.findById(userDTO.getRoleId()).
                orElseThrow(() -> new DataNotFoundException(MessageKeys.ROLE_NOT_FOUND));
        if (role.getRoleName().equalsIgnoreCase(RoleType.ADMIN)) { // không có quyền tạo ADMIN
            throw new BadCredentialsException(
                  MessageKeys.CAN_NOT_CREATE_ACCOUNT_ROLE_ADMIN);
        }

        // convert userDTO -> user
        User newUser = userMapper.toUser(userDTO);
        newUser.setRole(role);
        newUser.setActive(true); // mở tài khoản
        // kiểm tra xem nếu có accontId, không yêu cầu passowrd
        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            String password = userDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
        }

        return userRepository.save(newUser);
    }


    @Override
    public String login(String phoneNumber, String password) throws Exception {
        Optional<User> optionalUser=userRepository.findByPhoneNumber(phoneNumber);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException(MessageKeys.PHONE_NUMBER_AND_PASSWORD_FAILED);
        }
        User user =optionalUser.get();
        if(user.getFacebookAccountId() ==0 && user.getGoogleAccountId()==0){
           if(!passwordEncoder.matches(password,user.getPassword())){
               throw new BadCredentialsException(MessageKeys.PHONE_NUMBER_AND_PASSWORD_FAILED);
           }
        }
        if(!optionalUser.get().isActive()){
            throw new DataNotFoundException(MessageKeys.USER_ID_LOCKED);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getPhoneNumber(),password,user.getAuthorities()));
        String token = jwtTokenUtil.generateToken(user);


        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public User getUserDetailsFromToken(String token) throws Exception {
        if (jwtTokenUtil.isTokenExpirated(token)) {
            throw new Exception(MessageKeys.TOKEN_EXPIRATION_TIME);
        }
        String phoneNumber = jwtTokenUtil.extractPhoneNumber(token);
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new DataNotFoundException(MessageKeys.USER_NOT_FOUND);
        }
    }

    @Override
    public User updateUser(Long userId, UpdateUserDTO updateUserDTO) throws Exception {
        return null;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenDTO refreshTokenDTO) {
        return null;
    }

    @Override
    public Page<User> findAllUsers(String keyword, Pageable pageable) {
        try {
            if (keyword != null && !keyword.isEmpty()) {
                return userRepository.findByUsernameContainingIgnoreCase(keyword, pageable);
            } else {
                return userRepository.findAll(pageable);
            }
        } catch (Exception e) {

            return Page.empty(pageable);
        }
    }

    @Override
    public void resetPassword(Long userId, String newPassword) throws Exception {

    }

    @Override
    public void blockOrEnable(Long userId, Boolean active) throws DataNotFoundException {

    }
}
