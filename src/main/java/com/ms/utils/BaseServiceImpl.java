package com.ms.utils;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 2 * @Author: Gyl
 * 3 * @explain:
 * 4 * @Date: 2020/11/25 17:43
 * 5
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {



    protected Page<T> getPage() {
        return new Page(1, 15);
    }

    protected <D> boolean dtoAdd(D dto, Class<T> tClass) {
        int updateRow = 0;
        try {
            T entity = tClass.newInstance();
            BeanUtils.copyProperties(dto, entity);
            updateRow = baseMapper.insert(entity);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return updateRow > 0;
    }


    /**
     * 后台管理分页
     *
     * @param where
     * @param clazz
     * @param <V>
     * @return
     */
    protected <V> PageResult<V> pageList(LambdaQueryWrapper<T> where, Class<V> clazz) {
        IPage<V> viPage = this.voList(where, clazz);
        return new PageResult<>(viPage.getTotal(), viPage.getRecords());
    }


    /**
     * app端分页
     *
     * @param where
     * @param clazz
     * @param <V>
     * @return
     */
    protected <V> IPage<V> pageListByApp(LambdaQueryWrapper<T> where, Class<V> clazz) {
        IPage<V> viPage = this.voList(where, clazz);
        return viPage;
    }


    protected <V> IPage<V> voList(LambdaQueryWrapper<T> where, Class<V> clazz) {
        IPage<T> dbPage = this.page(getPage(), where);
        List<V> voList = dbPage.getRecords().stream().map(item -> {
            V vo = null;
            try {
                vo = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            // 将item中的值赋值给vo
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());
        dbPage.setRecords(null);
        IPage<V> vPage = new Page<>();
        BeanUtils.copyProperties(dbPage, vPage);
        vPage.setRecords(voList);
        return vPage;
    }


}
