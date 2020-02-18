package com.babyuser.service.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.user.BankMapper;
import com.pojo.TBankCard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service("BankService")
public class BankServiceImpl implements BankService {
    @Resource
    private BankMapper bankMapper;

    @Override
    public int insert(TBankCard entity) {
        return bankMapper.insert(entity);
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
    public int delete(Wrapper<TBankCard> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TBankCard entity) {
        return 0;
    }

    @Override
    public int update(TBankCard entity, Wrapper<TBankCard> updateWrapper) {
        return 0;
    }

    @Override
    public TBankCard selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TBankCard> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TBankCard> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TBankCard selectOne(Wrapper<TBankCard> queryWrapper) {
        return bankMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TBankCard> queryWrapper) {
        return null;
    }

    @Override
    public List<TBankCard> selectList(Wrapper<TBankCard> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TBankCard> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TBankCard> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TBankCard> selectPage(IPage<TBankCard> page, Wrapper<TBankCard> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TBankCard> page, Wrapper<TBankCard> queryWrapper) {
        return null;
    }
}
