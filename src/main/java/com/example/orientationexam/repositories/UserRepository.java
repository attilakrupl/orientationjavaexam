package com.example.orientationexam.repositories;

import com.example.orientationexam.models.User;
import com.example.orientationexam.models.UserGroup;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.group_id = :groupId")
    List<User> findByUserGroup(@Param("groupId") Long groupId);
}
