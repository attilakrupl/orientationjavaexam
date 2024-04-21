package com.example.orientationexam.repositories;

import com.example.orientationexam.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}