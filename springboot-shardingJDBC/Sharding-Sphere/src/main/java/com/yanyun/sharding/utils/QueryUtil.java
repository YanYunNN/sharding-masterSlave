package com.yanyun.sharding.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * 分页工具
 */
public class QueryUtil {
    /**
     * 分页
     * @param page     当前页
     * @param pageSize 页面数量
     * @param sortType 排序方式
     * @param sortCol  排序字段
     * @return 分页对象
     */
    public static PageRequest buildPageRequest(int page, int pageSize, String sortType, String sortCol) {
        Sort sort = null;
        if (!StringUtils.isEmpty(sortCol)) {
            if ("desc".equalsIgnoreCase(sortType)) {
                sort.by(Sort.Direction.DESC, sortCol);
            } else {
                sort.by(Sort.Direction.ASC, sortCol);
            }
            return PageRequest.of(page, pageSize, sort);
        }
        return PageRequest.of(page, pageSize);
    }

}
