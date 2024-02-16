package com.activity.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.activity.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
