package com.example.trello.service;

import com.example.trello.dto.LoginRequestDto;
import com.example.trello.dto.SignupRequestDto;
import com.example.trello.entity.Board;
import com.example.trello.entity.GroupEntity;
import com.example.trello.entity.User;
import com.example.trello.jwt.JwtUtil;
import com.example.trello.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String nickname = requestDto.getNickname();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        User user = new User(username, password, nickname);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        String accessToken = jwtUtil.createToken(user.getUsername());

        response.addHeader("Authorization", accessToken);
    }

    public User findUser(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(() ->
                new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
    }
}
