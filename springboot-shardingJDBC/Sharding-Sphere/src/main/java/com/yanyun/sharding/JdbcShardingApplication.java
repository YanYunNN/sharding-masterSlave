package com.yanyun.sharding;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 入口
 * @author 菜头君
 * @date 2018年5月19日
 */
@SpringBootApplication
public class JdbcShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcShardingApplication.class, args);
    }

    /**
     * 配置读写分离数据源
     * @return
     * @throws FileNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Bean
    public DataSource dataSource() throws FileNotFoundException, SQLException, IOException {
        System.out.println("-------加载数据源------------");
        return YamlShardingDataSourceFactory.createDataSource(ResourceUtils.getFile("config-sharding-master-slave.yml"));
    }
}
