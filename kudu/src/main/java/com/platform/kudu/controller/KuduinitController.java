package com.platform.kudu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platform.kudu.pojo.Kuduinit;
import com.platform.kudu.service.KuduinitService;

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
@RequestMapping("/init")
public class KuduinitController {

    @Autowired
    private KuduinitService kuduinitService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", kuduinitService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", kuduinitService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     * 测试：http://127.0.0.1:9012/kudu/init/search/1/1
     * body：
     * {
     * "title": 3
     * }
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody(required = false) Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Kuduinit> pageList = kuduinitService.findSearch(searchMap, page, size);
//        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Kuduinit>(pageList.getTotalElements(), pageList.getContent()));
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Kuduinit>(pageList.getTotalElements(),pageList.getTotalPages(), pageList.getContent()));
    }



    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody(required = false) Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", kuduinitService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param kuduinit
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Kuduinit kuduinit) {
        kuduinitService.add(kuduinit);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param kuduinit
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Kuduinit kuduinit, @PathVariable String id) {
        kuduinit.setId(id);
        kuduinitService.update(kuduinit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        kuduinitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
