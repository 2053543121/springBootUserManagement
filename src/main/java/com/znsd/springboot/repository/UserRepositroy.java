package com.znsd.springboot.repository;

import com.znsd.springboot.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepositroy extends JpaRepository<UserInfo,Integer>,JpaSpecificationExecutor<UserInfo>{
}
