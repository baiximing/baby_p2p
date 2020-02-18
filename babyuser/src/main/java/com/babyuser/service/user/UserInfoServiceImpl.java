package com.babyuser.service.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dao.user.UserInfoMapper;
import com.pojo.TUserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("UserInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(TUserInfo entity) {
        return userInfoMapper.insert(entity);
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
    public int delete(Wrapper<TUserInfo> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(TUserInfo entity) {
        return 0;
    }

    @Override
    public int update(TUserInfo entity, Wrapper<TUserInfo> updateWrapper) {
        return userInfoMapper.update(entity,updateWrapper);
    }

    @Override
    public TUserInfo selectById(Serializable id) {
        return null;
    }

    @Override
    public List<TUserInfo> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<TUserInfo> selectByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public TUserInfo selectOne(Wrapper<TUserInfo> queryWrapper) {
        return userInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TUserInfo> queryWrapper) {
        return null;
    }

    @Override
    public List<TUserInfo> selectList(Wrapper<TUserInfo> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<TUserInfo> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectObjs(Wrapper<TUserInfo> queryWrapper) {
        return null;
    }

    @Override
    public IPage<TUserInfo> selectPage(IPage<TUserInfo> page, Wrapper<TUserInfo> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<TUserInfo> page, Wrapper<TUserInfo> queryWrapper) {
        return null;
    }
}
