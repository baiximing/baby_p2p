package com.babyloan.service.Borrow;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.Borrow.BorrowMapper;
import com.pojo.TBorrow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("BorrowService")
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowMapper borrowMapper;


    @Override
    public int insert(TBorrow entity) {
        return borrowMapper.insert(entity);
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
    public int delete(Wrapper<TBorrow> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TBorrow entity) {
        return borrowMapper.updateById(entity);
    }

    @Override
    public int update(TBorrow entity, Wrapper<TBorrow> updateWrapper) {
        return borrowMapper.update(entity,updateWrapper);
    }

    @Override
    public TBorrow selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TBorrow> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TBorrow> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TBorrow selectOne(Wrapper<TBorrow> queryWrapper) {
        return borrowMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TBorrow> queryWrapper) {
        return null;
    }

    @Override
    public List<TBorrow> selectList(Wrapper<TBorrow> queryWrapper) {
        return borrowMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TBorrow> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TBorrow> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TBorrow> selectPage(IPage<TBorrow> page, Wrapper<TBorrow> queryWrapper) {
        return borrowMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TBorrow> page, Wrapper<TBorrow> queryWrapper) {
        return null;
    }
}
