package com.platform.kudu.pojo;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "git")
@SQLDelete(sql = "update git set del_flag = 1 where id = ?")//软删除
@Where(clause = "del_flag = 0")
@Component
public class Git implements Serializable {

    @Id
    private String id;//


    private String mark1;//
    private String mark2;//
    private String mark3;//
    private String mark4;//
    private String mark5;//
    private String mark6;//
    private java.util.Date create_time;//创建时间
    private java.util.Date update_time;//更新时间
    @Column(name = "del_flag")
    private String del_flag;//逻辑删除标记(0--正常 1--删除)


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMark3() {
        return mark3;
    }

    public void setMark3(String mark3) {
        this.mark3 = mark3;
    }

    public String getMark4() {
        return mark4;
    }

    public void setMark4(String mark4) {
        this.mark4 = mark4;
    }

    public String getMark5() {
        return mark5;
    }

    public void setMark5(String mark5) {
        this.mark5 = mark5;
    }

    public String getMark6() {
        return mark6;
    }

    public void setMark6(String mark6) {
        this.mark6 = mark6;
    }

    public java.util.Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(java.util.Date create_time) {
        this.create_time = create_time;
    }

    public java.util.Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(java.util.Date update_time) {
        this.update_time = update_time;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }


}
