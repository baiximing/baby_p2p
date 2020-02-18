package com.babyrepayment.service.Repayment;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.Repayment.RepaymentMapper;
import com.pojo.TRepayment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("RepaymentService")
public class RepaymentServiceImpl implements RepaymentService{
    @Resource
    public RepaymentMapper repaymentService;
    @Override
    public int insert(TRepayment entity) {
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
    public int delete(Wrapper<TRepayment> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TRepayment entity) {
        return 0;
    }

    @Override
    public int update(TRepayment entity, Wrapper<TRepayment> updateWrapper) {
        return repaymentService.update(entity,updateWrapper);
    }

    @Override
    public TRepayment selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TRepayment> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TRepayment> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TRepayment selectOne(Wrapper<TRepayment> queryWrapper) {
        return repaymentService.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TRepayment> queryWrapper) {
        return null;
    }

    @Override
    public List<TRepayment> selectList(Wrapper<TRepayment> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TRepayment> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TRepayment> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TRepayment> selectPage(IPage<TRepayment> page, Wrapper<TRepayment> queryWrapper) {
        return repaymentService.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TRepayment> page, Wrapper<TRepayment> queryWrapper) {
        return null;
    }
}
