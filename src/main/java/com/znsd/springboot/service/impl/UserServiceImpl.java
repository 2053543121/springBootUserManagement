package com.znsd.springboot.service.impl;

import com.znsd.springboot.entities.UserInfo;
import com.znsd.springboot.repository.UserRepositroy;
import com.znsd.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@EnableTransactionManagement  //首先使用注解@EnableTransactionManagement 开启事务支持后
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositroy userRepository;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Page<UserInfo> findAll(PageRequest pageRequest) {
       return userRepository.findAll(pageRequest);
    }

    @Override
    public Page<UserInfo> findAllName(Specification<UserInfo> specification, PageRequest pageRequest) {
        return userRepository.findAll(specification,pageRequest);
    }

    @Transactional
    @Override
    public void  save(UserInfo user) {
        user.setCreateDate(new Date());
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserInfo findOne(Integer id) {
        return userRepository.findById(id).get();
    }
}
