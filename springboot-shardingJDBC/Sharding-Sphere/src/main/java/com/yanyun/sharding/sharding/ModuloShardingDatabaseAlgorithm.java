package com.yanyun.sharding.sharding;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>选库算法</p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
@Slf4j
public final class ModuloShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

    /**
     * 选库
     */
    @Override
    public String doSharding(final Collection<String> databaseNames, final PreciseShardingValue<Long> shardingValue) {

        log.info("databaseNames:{}", JSON.toJSONString(databaseNames));
        log.info("shardingValue:{}", JSON.toJSONString(shardingValue));

        String databaseName = "";

        // 只做单库分表
        if ("t_user".equalsIgnoreCase(shardingValue.getLogicTableName())) {
            databaseName = (String) databaseNames.toArray()[0];
        }
        if ("t_file".equalsIgnoreCase(shardingValue.getLogicTableName())) {
            databaseName = (String) databaseNames.toArray()[0];
        }
        // 分库分表
        if ("t_order".equalsIgnoreCase(shardingValue.getLogicTableName()) ||
                "t_order_item".equalsIgnoreCase(shardingValue.getLogicTableName())) {
            for (String each : databaseNames) {
                if (each.endsWith(shardingValue.getValue() % databaseNames.size() + "")) {
                    databaseName = each;
                    break;
                }
            }
        }
        log.info("databaseName:{}", databaseName);
        if (!StringUtils.isEmpty(databaseName)) {
            return databaseName;
        }
        throw new UnsupportedOperationException();
    }


    /**
     * 实现between and查询
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        log.info("collection:{}", JSON.toJSONString(collection));
        log.info("rangeShardingValue:{}", JSON.toJSONString(rangeShardingValue));

        Collection<String> collect = new ArrayList<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();

        log.info("valueRange:{}", JSON.toJSONString(valueRange));
        log.info("lowerEndpoint:{}", valueRange.lowerEndpoint());
        log.info("upperEndpoint:{}", valueRange.upperEndpoint());


        for (Long i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % collection.size() + "")) {
                    collect.add(each);
                }
            }
        }

        log.info("collect:{}", JSON.toJSONString(collect));

        return collect;
    }
}