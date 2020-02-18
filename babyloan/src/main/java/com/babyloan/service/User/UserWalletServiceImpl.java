package com.babyloan.service.User;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.user.UserWalletMapper;
import com.pojo.TUserWallet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("UserWalletService")
public class UserWalletServiceImpl implements UserWalletService {

    @Resource
    private  UserWalletMapper userWalletMapper;

    @Override
    public int insert(TUserWallet entity) {
        return userWalletMapper.insert(entity);
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
    public int delete(Wrapper<TUserWallet> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TUserWallet entity) {

        return userWalletMapper.updateById(entity);
    }

    @Override
    public int update(TUserWallet entity, Wrapper<TUserWallet> updateWrapper) {
        return userWalletMapper.update(entity,updateWrapper);
    }

    @Override
    public TUserWallet selectById(Serializable id) {
        return userWalletMapper.selectById(id);
    }

    @Override
    public List<TUserWallet> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TUserWallet> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TUserWallet selectOne(Wrapper<TUserWallet> queryWrapper) {
        return userWalletMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TUserWallet> queryWrapper) {
        return null;
    }

    @Override
    public List<TUserWallet> selectList(Wrapper<TUserWallet> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TUserWallet> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TUserWallet> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TUserWallet> selectPage(IPage<TUserWallet> page, Wrapper<TUserWallet> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TUserWallet> page, Wrapper<TUserWallet> queryWrapper) {
        return null;
    }
}
