package com.platform.kudu;

import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;
import utils.JsonObjectConverter;

@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients
//@EnableDiscoveryClient
public class KuduApplication {

    public static void main(String[] args) {
        SpringApplication.run(KuduApplication.class, args);
    }


    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }

    @Bean
    public JsonObjectConverter jsonObjectConverter() {
        return new JsonObjectConverter();
    }

    @Bean
    public JSONUtil jsonUtil() {
        return new JSONUtil();
    }
}
