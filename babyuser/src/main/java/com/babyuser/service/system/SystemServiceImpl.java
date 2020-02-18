package com.babyuser.service.system;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.system.SystemMapper;
import com.pojo.TSystemDictionaryItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("SysemService")
public class SystemServiceImpl implements  SysemService {

    @Resource
    private SystemMapper systemMapper;

    @Override
    public int insert(TSystemDictionaryItem entity) {
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
    public int delete(Wrapper<TSystemDictionaryItem> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TSystemDictionaryItem entity) {
        return 0;
    }

    @Override
    public int update(TSystemDictionaryItem entity, Wrapper<TSystemDictionaryItem> updateWrapper) {
        return 0;
    }

    @Override
    public TSystemDictionaryItem selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TSystemDictionaryItem> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TSystemDictionaryItem> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TSystemDictionaryItem selectOne(Wrapper<TSystemDictionaryItem> queryWrapper) {
        return null;
    }

    @Override
    public Integer selectCount(Wrapper<TSystemDictionaryItem> queryWrapper) {
        return null;
    }

    @Override
    public List<TSystemDictionaryItem> selectList(Wrapper<TSystemDictionaryItem> queryWrapper) {
        return systemMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TSystemDictionaryItem> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TSystemDictionaryItem> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TSystemDictionaryItem> selectPage(IPage<TSystemDictionaryItem> page, Wrapper<TSystemDictionaryItem> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TSystemDictionaryItem> page, Wrapper<TSystemDictionaryItem> queryWrapper) {
        return null;
    }
}
