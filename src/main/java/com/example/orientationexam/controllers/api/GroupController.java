package com.example.orientationexam.controllers.api;

import com.example.orientationexam.dtos.GroupGenerationRequest;
import com.example.orientationexam.dtos.UserCreationRequest;
import com.example.orientationexam.models.Group;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/api")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;
    @PostMapping("/groups/generate")
    public ResponseEntity<?> generateGroups(@RequestBody GroupGenerationRequest request){
        if(request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing groupLimit"));
        }
        List<User> allUsers = userService.getAllUsers();
        for(User user : allUsers) {
            user.setForeignGroup(null);
        }
        groupService.deleteGroups();

        List<Long> groupIDs = new ArrayList<>();
        for(int i = 0; i < request.getGroupLimit(); ++i) {
            groupIDs.add(groupService.createGroup().getGid());
        }
        for(Long id : groupIDs) {
            System.out.println(id);
        }

        for(User user : allUsers) {
            Random rand = new Random();
            user.setForeignGroup(groupService.getGroupById(groupIDs.get(rand.nextInt(groupIDs.size()))));
            userService.saveExistingUser(user);
        }

        List<Group> gl = groupService.getAllGroups();
        for(Group g : gl) {
            g.setUsers(groupService.getAllUsersByGroupID(g.getGid()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", gl));
    }
}
