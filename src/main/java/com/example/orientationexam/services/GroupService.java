package com.example.orientationexam.services;

import com.example.orientationexam.models.UserGroup;
import com.example.orientationexam.models.User;
import com.example.orientationexam.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    public List<UserGroup> getAllGroups() {
        List<UserGroup> lg = groupRepository.findAll();
        if(lg.isEmpty())
        {
            System.out.println("there are no groups!!!");
        }
        return lg;
    }

    public UserGroup createGroup() {
        UserGroup newGroup = new UserGroup();
        newGroup.setCreatedAt(LocalDate.now());
        groupRepository.save(newGroup);
        newGroup = groupRepository.findById(newGroup.getId()).get();
        newGroup.setName("Group " + newGroup.getId());
        groupRepository.save(newGroup);
        return newGroup;
    }

    public void deleteGroups() {
        groupRepository.deleteAll();
    }

    public UserGroup getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public void updateGroup(UserGroup ug) {
        groupRepository.save(ug);
    }

    public List<User> getAllUsersByGroupID(Long groupId) {
        Optional<UserGroup> optGroup =groupRepository.findById(groupId);
        List<User> lu = new ArrayList<>();
        if(optGroup.isPresent())
        {
            lu = optGroup.get().getUsers();
        }
        return lu;
    }
}
