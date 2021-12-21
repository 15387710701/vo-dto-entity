package com.ms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.domain.UmsMember;
import com.ms.mapper.UmsMemberMapper;
import com.ms.service.IUmsMemberService;
import com.ms.utils.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author:SmartV
 * @date:2021/12/21 15:47
 */
@Service
public class UmsMemberServiceImpl extends BaseServiceImpl<UmsMemberMapper, UmsMember> implements IUmsMemberService {
    @Override
    protected Page<UmsMember> getPage() {
        return new Page(1,10) ;
    }
}
