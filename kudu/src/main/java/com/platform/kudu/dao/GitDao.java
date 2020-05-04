package com.platform.kudu.dao;

import com.platform.kudu.pojo.Git;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface GitDao extends JpaRepository<Git,String>,JpaSpecificationExecutor<Git>{
	
}
