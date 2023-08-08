package com.example.trello.service;

import com.example.trello.entity.GroupEntity;
import com.example.trello.entity.User;
import com.example.trello.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public boolean userBelongsToGroup(User user, GroupEntity group) {
        return user.getUserGroups() != null && user.getUserGroups().equals(group);
    }
}
