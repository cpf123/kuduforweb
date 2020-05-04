package com.platform.kudu.pojo;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.JsonObjectConverter;
import utils.JsonUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author caopf
 */
@Entity
@Table(name = "kuduinit")
@SQLDelete(sql = "update kuduinit set del_flag = 1 where id = ?")//软删除
@Where(clause = "del_flag = 0")
@Component
public class Kuduinit implements Serializable {

    public Kuduinit() {
    }

    @Id
    private String id;//ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Column(columnDefinition = "json")
    @Convert(converter = JsonObjectConverter.class)
    private Object param;

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    private String kuduname;
    private String mainclass;
    private String mark1;
    private String mark2;
    private java.util.Date create_time;
    private java.util.Date update_time;
    @Column(name = "del_flag")
    private String del_flag;


    public String getKuduname() {
        return kuduname;
    }

    public void setKuduname(String kuduname) {
        this.kuduname = kuduname;
    }

    public String getMainclass() {
        return mainclass;
    }

    public void setMainclass(String mainclass) {
        this.mainclass = mainclass;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }


}
