package com.example.trello.service;

import com.example.trello.entity.Board;
import com.example.trello.entity.User;
import com.example.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(() ->
                new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
    }
}
