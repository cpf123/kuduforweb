package com.platform.kudu.service;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.platform.kudu.dao.GitDao;
import com.platform.kudu.pojo.Git;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import utils.IdWorker;


/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class GitService {

    @Autowired
    private GitDao gitDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Git> findAll() {
        return gitDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Git> findSearch(Map whereMap, int page, int size) {
        Specification<Git> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return gitDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Git> findSearch(Map whereMap) {
        Specification<Git> specification = createSpecification(whereMap);
        return gitDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Git findById(String id) {
        return gitDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param git
     */
    public void add(Git git) {
//        git.setId(idWorker.nextId() + "");
//        git.setId(id);
        Date create_time = new Date();
        git.setCreate_time(create_time);
        git.setDel_flag("0");
        gitDao.save(git);
    }

    /**
     * 修改
     *
     * @param git
     */
    public void update(Git git) {
        gitDao.save(git);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        gitDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Git> createSpecification(Map searchMap) {

        return new Specification<Git>() {

            @Override
            public Predicate toPredicate(Root<Git> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 
                if (searchMap.get("mark1") != null && !"".equals(searchMap.get("mark1"))) {
                    predicateList.add(cb.like(root.get("mark1").as(String.class), "%" + (String) searchMap.get("mark1") + "%"));
                }
                // 
                if (searchMap.get("mark2") != null && !"".equals(searchMap.get("mark2"))) {
                    predicateList.add(cb.like(root.get("mark2").as(String.class), "%" + (String) searchMap.get("mark2") + "%"));
                }
                // 
                if (searchMap.get("mark3") != null && !"".equals(searchMap.get("mark3"))) {
                    predicateList.add(cb.like(root.get("mark3").as(String.class), "%" + (String) searchMap.get("mark3") + "%"));
                }
                // 
                if (searchMap.get("mark4") != null && !"".equals(searchMap.get("mark4"))) {
                    predicateList.add(cb.like(root.get("mark4").as(String.class), "%" + (String) searchMap.get("mark4") + "%"));
                }
                // 
                if (searchMap.get("mark5") != null && !"".equals(searchMap.get("mark5"))) {
                    predicateList.add(cb.like(root.get("mark5").as(String.class), "%" + (String) searchMap.get("mark5") + "%"));
                }
                // 
                if (searchMap.get("mark6") != null && !"".equals(searchMap.get("mark6"))) {
                    predicateList.add(cb.like(root.get("mark6").as(String.class), "%" + (String) searchMap.get("mark6") + "%"));
                }
                // 逻辑删除标记(0--正常 1--删除)
                if (searchMap.get("del_flag") != null && !"".equals(searchMap.get("del_flag"))) {
                    predicateList.add(cb.like(root.get("del_flag").as(String.class), "%" + (String) searchMap.get("del_flag") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
