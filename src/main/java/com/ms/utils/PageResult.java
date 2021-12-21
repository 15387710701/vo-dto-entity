package com.ms.utils;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author mall
 */
@Data
@Builder
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -275582248840137389L;

    private Long total;
    /**
     * 当前页结果集
     */
    private List<T> records;

    public PageResult(Long total, List<T> records) {
        this.total = total;
        this.records = records;
    }
}
