package com.example.orientationexam.services;

import com.example.orientationexam.models.Group;
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
    public List<Group> getAllGroups() {
        List<Group> lg = groupRepository.findAll();
        if(lg.isEmpty())
        {
            System.out.println("there are no groups!!!");
        }
        return lg;
    }

    public Group createGroup() {
        Group newGroup = new Group();
        newGroup.setCreatedAt(LocalDate.now());
        groupRepository.save(newGroup);
        newGroup = groupRepository.findById(newGroup.getGid()).get();
        newGroup.setGroupName("Group " + newGroup.getGid());
        groupRepository.save(newGroup);
        return newGroup;
    }

    public void deleteGroups() {
        groupRepository.deleteAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsersByGroupID(Long groupId) {
        Optional<Group> optGroup =groupRepository.findById(groupId);
        List<User> lu = new ArrayList<>();
        if(optGroup.isPresent())
        {
            lu = optGroup.get().getUsers();
        }
        return lu;
    }
}
