package com.platform.kudu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import utils.IdWorker;

import com.platform.kudu.dao.KuduinitDao;
import com.platform.kudu.pojo.Kuduinit;

/**
 * 服务层
 *
 * @author
 */
@Service
public class KuduinitService {

    @Autowired
    private KuduinitDao kuduinitDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 初始化：hive->kudu
     */

    /**
     * 查询全部列表
     *
     * @return
     */
    //@Query(value = "select f from Kuduinit f order by f.create_time desc ",nativeQuery = true)
    public List<Kuduinit> findAll() {
        return kuduinitDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @param DESC
     * @return
     */
    public Page<Kuduinit> findSearch(Map whereMap, int page, int size) {
        Specification<Kuduinit> specification = createSpecification(whereMap);
        Sort sort = new Sort(Sort.Direction.DESC, "id");//倒序查询
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<Kuduinit> search = kuduinitDao.findAll(specification, pageRequest);
        List<Kuduinit> searchContent = search.getContent();

        return kuduinitDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Kuduinit> findSearch(Map whereMap) {
        Specification<Kuduinit> specification = createSpecification(whereMap);
        return kuduinitDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Kuduinit findById(String id) {
        return kuduinitDao.findById(id).get();
    }

    /**
     * 增加
     *
     * @param kuduinit
     */
    public void add(Kuduinit kuduinit) {
        kuduinit.setId(idWorker.nextId() + "");
        Date create_time = new Date();
        kuduinit.setCreate_time(create_time);
        kuduinit.setDel_flag("0");
        kuduinitDao.save(kuduinit);

    }


    /**
     * 修改
     *
     * @param kuduinit
     */
    public void update(Kuduinit kuduinit) {
        Date update_time = new Date();
        kuduinit.setUpdate_time(update_time);
        kuduinitDao.save(kuduinit);

    }
    public void updatelog(Kuduinit kuduinit) {
        kuduinitDao.save(kuduinit);

    }
    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        kuduinitDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Kuduinit> createSpecification(Map searchMap) {

        return new Specification<Kuduinit>() {

            @Override
            public Predicate toPredicate(Root<Kuduinit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                if (searchMap.get("param") != null && !"".equals(searchMap.get("param"))) {
                    predicateList.add(cb.like(root.get("param").as(String.class), "%" + (String) searchMap.get("param") + "%"));
                }
                //
                if (searchMap.get("kuduname") != null && !"".equals(searchMap.get("kuduname"))) {
                    predicateList.add(cb.like(root.get("kuduname").as(String.class), "%" + (String) searchMap.get("kuduname") + "%"));
                }
                //
                if (searchMap.get("mainclass") != null && !"".equals(searchMap.get("mainclass"))) {
                    predicateList.add(cb.like(root.get("mainclass").as(String.class), "%" + (String) searchMap.get("mainclass") + "%"));
                }
                //
                if (searchMap.get("mark1") != null && !"".equals(searchMap.get("mark1"))) {
                    predicateList.add(cb.like(root.get("mark1").as(String.class), "%" + (String) searchMap.get("mark1") + "%"));
                }
                //
                if (searchMap.get("mark2") != null && !"".equals(searchMap.get("mark2"))) {
                    predicateList.add(cb.like(root.get("mark2").as(String.class), "%" + (String) searchMap.get("mark2") + "%"));
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
