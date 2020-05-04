package com.platform.user.controller;

import java.util.Map;


import com.alibaba.fastjson.JSON;
import entity.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platform.user.pojo.User;
import com.platform.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public LoginResult login() {
        String str = "{\"token\": \"A0193-103AC-VV224-12334-45134\",\"name\": \"小白\",\"avatar\": \"http://www.jiedaibao.com/static/img/logo.png\"}";
        Object parse = JSON.parse(str);
        return new LoginResult(StatusCode.OK, parse);
    }
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public LoginResult getinfo() {
        String getinfo="{\"roles\": [\"admin\"],\"role\": [\"admin\"],\"name\": \"admin\",\"avatar\": \"http://www.jiedaibao.com/static/img/logo.png\"}";
        Object parse = JSON.parse(getinfo);
        return new LoginResult(StatusCode.OK, parse);
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public LoginResult logout() {
        String logout="{\"token\":\"admin\"}";
        Object parse = JSON.parse(logout);
        return new LoginResult(StatusCode.OK, parse);
    }
    /**
     * 查询全部数据
     * @return
     */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(),pageList.getTotalPages(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
//    @RequestMapping(method = RequestMethod.POST)
//    public Result add(@RequestBody User user) {
//        userService.add(user);
//        return new Result(true, StatusCode.OK, "增加成功");
//    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        userService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
