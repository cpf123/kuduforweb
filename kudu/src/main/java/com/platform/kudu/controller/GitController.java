package com.platform.kudu.controller;


import com.platform.kudu.pojo.Git;
import com.platform.kudu.pojo.Kuduinit;
import com.platform.kudu.process.LogReader;
import com.platform.kudu.process.Package;
import com.platform.kudu.service.GitService;
import com.platform.kudu.service.KuduinitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/kuduinit")
public class GitController {
    @Autowired
    private Package runshell;

    @Autowired
    private LogReader logReader;

    @Autowired
    private GitService gitService;

    @Autowired
    KuduinitService kuduinitService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", gitService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", gitService.findById(id));
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
    public Result findSearch(@RequestBody(required = false) Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Git> pageList = gitService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Git>(pageList.getTotalElements(), pageList.getTotalPages(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody(required = false) Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", gitService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param git
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Git git) {
        gitService.add(git);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param git
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Git git, @PathVariable String id) {
        git.setId(id);
        gitService.update(git);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        gitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    @Transactional// 原子操作
    @RequestMapping(value = "/runshell/{id}", method = RequestMethod.GET)
    public Result runShell(@PathVariable String id) {
        try {
            Result result = new Result(true, StatusCode.OK, "查询成功", runshell.executeShell(id, "git@git.jdb-dev.com:zhaojt/com.jdb.ploandp.rtc.git","com.jdb.ploandp.rtc"));

            if (result.isFlag()) {
//                System.out.println("返回值成功");
                Kuduinit kuduinit = kuduinitService.findById(id);
                kuduinit.setMark1("success");
                kuduinitService.updatelog(kuduinit);
            } else {
                Kuduinit kuduinit = kuduinitService.findById(id);
                kuduinit.setMark1("failed");
                kuduinitService.updatelog(kuduinit);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Transactional
    @RequestMapping(value = "/readlog/{id}", method = RequestMethod.GET)
    public Result readKuduInitLog(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", logReader.readLog(id));
    }

}
