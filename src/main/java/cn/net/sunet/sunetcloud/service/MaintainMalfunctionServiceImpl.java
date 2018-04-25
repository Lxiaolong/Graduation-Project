package cn.net.sunet.sunetcloud.service;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/20
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.dao.MaintainMalfunctionMapper;
import cn.net.sunet.sunetcloud.domain.MaintainMalfunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lxiaolong
 */
@Service
public class MaintainMalfunctionServiceImpl {
    @Autowired
    private MaintainMalfunctionMapper maintainMalfunctionMapper;

    public int insert(MaintainMalfunction maintainMalfunction) {
        return maintainMalfunctionMapper.insert(maintainMalfunction);
    }

    public void update(MaintainMalfunction maintainMalfunction) {
        maintainMalfunctionMapper.updateByPrimaryKeySelective(maintainMalfunction);
    }
    public MaintainMalfunction selectById(Long id){
       return maintainMalfunctionMapper.selectById(id);
    }

}
