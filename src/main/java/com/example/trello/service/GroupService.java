package com.example.trello.service;

import com.example.trello.dto.CardResponseDto;
import com.example.trello.entity.Card;
import com.example.trello.entity.GroupEntity;
import com.example.trello.entity.User;
import com.example.trello.entity.UserGroup;
import com.example.trello.repository.GroupRepository;
import com.example.trello.repository.UserGroupRepository;
import com.example.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;

    public boolean userBelongsToGroup(User user, GroupEntity group) {
        return user.getUserGroups() != null && user.getUserGroups().equals(group);
    }

    public void createGroup(User user) {
        GroupEntity group = groupRepository.save(new GroupEntity());
        UserGroup userGroup = new UserGroup(user, group);
        userGroupRepository.save(userGroup);
    }
}
