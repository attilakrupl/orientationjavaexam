package com.example.orientationexam.repositories;

import com.example.orientationexam.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<UserGroup, Long> {
}
