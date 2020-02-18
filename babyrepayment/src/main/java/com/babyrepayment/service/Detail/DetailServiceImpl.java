package com.babyrepayment.service.Detail;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.Detail.DetailMapper;
import com.pojo.TRepaymentDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Service("DetailService")
public class DetailServiceImpl implements DetailService{
    @Resource
    public DetailMapper detailMapper;

    @Override
    public int insert(TRepaymentDetail entity) {
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
    public int delete(Wrapper<TRepaymentDetail> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TRepaymentDetail entity) {
        return 0;
    }

    @Override
    public int update(TRepaymentDetail entity, Wrapper<TRepaymentDetail> updateWrapper) {
        return 0;
    }

    @Override
    public TRepaymentDetail selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TRepaymentDetail> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TRepaymentDetail> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TRepaymentDetail selectOne(Wrapper<TRepaymentDetail> queryWrapper) {
        return null;
    }

    @Override
    public Integer selectCount(Wrapper<TRepaymentDetail> queryWrapper) {
        return null;
    }

    @Override
    public List<TRepaymentDetail> selectList(Wrapper<TRepaymentDetail> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TRepaymentDetail> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TRepaymentDetail> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TRepaymentDetail> selectPage(IPage<TRepaymentDetail> page, Wrapper<TRepaymentDetail> queryWrapper) {
        return detailMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TRepaymentDetail> page, Wrapper<TRepaymentDetail> queryWrapper) {
        return null;
    }
}
