package com.example.trello.service;

import com.example.trello.dto.LoginRequestDto;
import com.example.trello.dto.SignupRequestDto;
import com.example.trello.entity.User;
import com.example.trello.entity.UserRoleEnum;
import com.example.trello.jwt.JwtUtil;
import com.example.trello.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String nickname = signupRequestDto.getNickname();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;

        if(userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("중복된 닉네임 입니다.");
        }

        User user = new User(username, nickname, password, role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록되지 않은 아이디 입니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER,
                jwtUtil.createToken(user.getUsername(), user.getRole()));
    }

}
