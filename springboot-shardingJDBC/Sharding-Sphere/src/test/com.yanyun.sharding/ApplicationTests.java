package com.yanyun.sharding;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApplicationTests {

    @BeforeClass
    public static void init() throws SQLException, IOException {
        System.out.println("dataSource");

    }

    @Test
    public void test() throws IOException, SQLException {
        String sharding_file = "classpath:config-sharding.yml";
        String master_slave_file = "classpath:config-master-slave.yml";
        String sharding_master_slave_file = "classpath:config-sharding-master-slave.yml";

        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(ResourceUtils.getFile(sharding_master_slave_file));
        System.out.println(dataSource);
    }


}

