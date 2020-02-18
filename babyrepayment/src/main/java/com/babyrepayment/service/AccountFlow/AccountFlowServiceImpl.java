package com.babyrepayment.service.AccountFlow;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.AccountFlow.AccountFlowMapper;
import com.pojo.TAccountFlow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("AccountFlowService")
public class AccountFlowServiceImpl implements AccountFlowService{

    @Resource
    public AccountFlowMapper accountFlowMapper;

    @Override
    public int insert(TAccountFlow entity) {
        return accountFlowMapper.insert(entity);
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
    public int delete(Wrapper<TAccountFlow> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TAccountFlow entity) {
        return 0;
    }

    @Override
    public int update(TAccountFlow entity, Wrapper<TAccountFlow> updateWrapper) {
        return 0;
    }

    @Override
    public TAccountFlow selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TAccountFlow> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TAccountFlow> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TAccountFlow selectOne(Wrapper<TAccountFlow> queryWrapper) {
        return null;
    }

    @Override
    public Integer selectCount(Wrapper<TAccountFlow> queryWrapper) {
        return null;
    }

    @Override
    public List<TAccountFlow> selectList(Wrapper<TAccountFlow> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TAccountFlow> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TAccountFlow> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TAccountFlow> selectPage(IPage<TAccountFlow> page, Wrapper<TAccountFlow> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TAccountFlow> page, Wrapper<TAccountFlow> queryWrapper) {
        return null;
    }
}
