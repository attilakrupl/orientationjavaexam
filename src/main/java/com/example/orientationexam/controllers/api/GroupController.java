package com.example.orientationexam.controllers.api;

import com.example.orientationexam.dtos.GroupGenerationRequest;
import com.example.orientationexam.models.UserGroup;
import com.example.orientationexam.models.User;
import com.example.orientationexam.services.GroupService;
import com.example.orientationexam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/api")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;
    @PostMapping("/groups/generate")
    public ResponseEntity<?> generateGroups(@RequestBody(required=false) GroupGenerationRequest request){
        if(request==null || request.getGroupLimit()==0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing groupLimit"));
        }
        List<User> allUsers = userService.getAllUsers();
        for(User user : allUsers) {
            user.setGroup_id(null);
        }
        userService.updateUsers(allUsers);
        groupService.deleteGroups();
        int groupsNeeded = allUsers.size() % request.getGroupLimit() == 0 ? allUsers.size() / request.getGroupLimit() : allUsers.size() / request.getGroupLimit() + 1;
        List<Long> groupIDs = new ArrayList<>();
        for(int i = 0; i < groupsNeeded; ++i) {
            groupIDs.add(groupService.createGroup().getId());
        }

        Collections.shuffle(allUsers);

        int actualGroupIdx = 0;
        for(User user : allUsers) {
            if(userService.getAllUsersForGroup(groupService.getGroupById(groupIDs.get(actualGroupIdx))).size()>=request.getGroupLimit())
            {
                ++actualGroupIdx;
            }
            user.setGroup_id(groupIDs.get(actualGroupIdx));
            userService.updateUser(user);

        }

        List<UserGroup> gl = groupService.getAllGroups();
        for(var g : gl) {
            var users = userService.getAllUsersForGroup(g);
            g.setUsers(users);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("groups", gl));
    }
}
