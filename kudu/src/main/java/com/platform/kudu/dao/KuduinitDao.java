package com.platform.kudu.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.platform.kudu.pojo.Kuduinit;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
//@FeignClient(value = "platform-kudu")
public interface KuduinitDao extends JpaRepository<Kuduinit, String>, JpaSpecificationExecutor<Kuduinit> {

}
