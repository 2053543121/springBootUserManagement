package com.znsd.springboot.controller;

import com.znsd.springboot.entities.UserInfo;
import com.znsd.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @ResponseBody
    @RequestMapping(value = "/postIndex",method = RequestMethod.POST)
    public Map<String,Object> postIndex(com.znsd.springboot.entities.Page page){
        //System.out.println("page :    "+page);
        Map<String,Object> map=new HashMap<String,Object>();
        if(page.getSearch() == null || "".equals(page.getSearch())){
            PageRequest  pageRequest= new PageRequest(page.getPage()-1,page.getLimit());
            Page<UserInfo> pa = userService.findAll(pageRequest);
            map.put("total",pa.getTotalElements());
            map.put("rows",pa.getContent());
        }else{
            PageRequest  pageRequest= new PageRequest(page.getPage()-1,page.getLimit());
            Specification<UserInfo> specification = new Specification<UserInfo>() {
                @Override
                public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                    Path<String> path = root.get("userName");
                    Predicate  predicate= cb.like(path,page.getSearch());
                    return predicate;
                }
            };
            Page<UserInfo> pa = userService.findAllName(specification,pageRequest);
            map.put("total",pa.getTotalElements());
            map.put("rows",pa.getContent());
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/addPost",method = RequestMethod.POST)
    public boolean addPost(UserInfo user){
        userService.save(user);
        return true;
    }
    @ResponseBody
    @RequestMapping(value = "/delPost",method = RequestMethod.POST)
    public boolean delPost(UserInfo user){
        userService.delete(user.getId());
        return true;
    }
    @ResponseBody
    @RequestMapping(value = "/updSelectPost",method = RequestMethod.POST)
    public Map<String,Object> updSelectPost(UserInfo user){
        Map<String,Object> map=new HashMap<String,Object>();
        UserInfo u = userService.findOne(user.getId());
        map.put("user",u);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/updPost",method = RequestMethod.POST)
    public boolean updPost(UserInfo user){
        UserInfo u = userService.findOne(user.getId());
        user.setCreateDate(u.getCreateDate());
        userService.save(user);
        return true;
    }
}
