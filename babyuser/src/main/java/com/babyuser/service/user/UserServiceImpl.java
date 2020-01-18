package com.babyuser.service.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.user.UserMapper;
import com.pojo.TUserAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("UserService")
public class UserServiceImpl implements  UserService {

    @Resource
    private  UserMapper userMapper;


    @Override
    public int insert(TUserAccount entity) {
        return userMapper.insert(entity);
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
    public int delete(Wrapper<TUserAccount> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TUserAccount entity) {
        return userMapper.updateById(entity);
    }

    @Override
    public int update(TUserAccount entity, Wrapper<TUserAccount> updateWrapper) {
        return 0;
    }

    @Override
    public TUserAccount selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TUserAccount> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TUserAccount> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TUserAccount selectOne(Wrapper<TUserAccount> queryWrapper) {
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TUserAccount> queryWrapper) {
        return null;
    }

    @Override
    public List<TUserAccount> selectList(Wrapper<TUserAccount> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TUserAccount> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TUserAccount> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TUserAccount> selectPage(IPage<TUserAccount> page, Wrapper<TUserAccount> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TUserAccount> page, Wrapper<TUserAccount> queryWrapper) {
        return null;
    }
}
