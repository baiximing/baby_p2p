package com.babyuser.service.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.system.SystemLogMapper;
import com.pojo.TLoginLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("SystemLogService")
public class SystemLogServiceImpl implements SystemLogService {

    @Resource
    private SystemLogMapper systemLogMapper;

    @Override
    public int insert(TLoginLog entity) {
        return 0;
    }

    @Override
    public int deleteById(Serializable id) {
        return 0;
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return 0;
    }

    @Override
    public int delete(Wrapper<TLoginLog> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TLoginLog entity) {
        return 0;
    }

    @Override
    public int update(TLoginLog entity, Wrapper<TLoginLog> updateWrapper) {
        return 0;
    }

    @Override
    public TLoginLog selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TLoginLog> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TLoginLog> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TLoginLog selectOne(Wrapper<TLoginLog> queryWrapper) {
        return null;
    }

    @Override
    public Integer selectCount(Wrapper<TLoginLog> queryWrapper) {
        return null;
    }

    @Override
    public List<TLoginLog> selectList(Wrapper<TLoginLog> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TLoginLog> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TLoginLog> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TLoginLog> selectPage(IPage<TLoginLog> page, Wrapper<TLoginLog> queryWrapper) {
        return systemLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TLoginLog> page, Wrapper<TLoginLog> queryWrapper) {
        return null;
    }
}
