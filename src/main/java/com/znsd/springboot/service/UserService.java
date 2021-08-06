package com.znsd.springboot.service;

import com.znsd.springboot.entities.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {
    public Page<UserInfo> findAll(PageRequest pageRequest);
    public Page<UserInfo> findAllName(Specification<UserInfo> specification,PageRequest  pageRequest);
    public void save(UserInfo user);
    public void delete(Integer id);
    public UserInfo findOne(Integer id);
}
