package com.ecomorce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomorce.model.UserDeatails;

@Repository
public interface UserDeatailsRepository extends JpaRepository<UserDeatails, Long>{

public List<UserDeatails> findByUserName(String userName);
public List<UserDeatails> findByUserNameAndPassword(String userName, String password);
public List<UserDeatails> findByUserType(String role);

}
