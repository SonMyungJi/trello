package com.example.trello.auth;

import com.example.trello.entity.User;
import com.example.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        Set<GrantedAuthority> authorities = new HashSet<>();

        // 사용자 그룹을 기반으로 권한 부여
        user.getGroups().forEach(group -> {
            String groupId = "ROLE_" + group.getId();
            authorities.add(new SimpleGrantedAuthority(groupId));
        });

        return new UserDetailsImpl(user, authorities);
    }
}
