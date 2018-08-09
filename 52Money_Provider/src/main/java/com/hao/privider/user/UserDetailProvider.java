package com.hao.privider.user;

import com.hao.core.util.ExecuteUtils;
import com.hao.core.vo.R;
import com.hao.domain.oss.OSSPo;
import com.hao.domain.user.UserDetail;
import com.hao.mapper.oss.OSSPoMapper;
import com.hao.mapper.user.OpLogMapper;
import com.hao.mapper.user.UserDatailMapper;
import com.hao.service.user.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailProvider implements UserDetailService {
   @Autowired
   private UserDatailMapper mapper;

   @Autowired
   private OSSPoMapper ossPoMapper;

   @Autowired
   private OpLogMapper logMapper;


    @Override
    public R save(int uid) {

        return ExecuteUtils.getR(mapper.insert(uid),"初始化用户详情完成");
    }

    @Override
    public R update(UserDetail detail) {

        return ExecuteUtils.getR(mapper.updateById(detail),"个人实名认证");

    }

    @Override
    public UserDetail queryByUid(int uid) {
        return mapper.selectByUid(uid);
    }

    @Override
    public List<UserDetail> queryByFlag(int flag) {
        return mapper.selectByFlag(flag);
    }

    @Override
    public R realNameAuth(int uid) {
        UserDetail detail = mapper.selectByUid(uid);
        R r = null;
        switch (detail.getFlag()){
            case 0://初始化 需要认证 detail 详情
                r = new R(1001,"请完成实名认证",detail);
                break;
            case 1://认证审核中
                r = new R(1002,"实名认证审核中",detail);
                break;
            case 2://认证通过
                r = new R(1003,"实名认证审核通过",detail);
                break;
            case 3://认证拒绝
                r = new R(1004,"实名认证审核失败",detail);
                break;
        }
        return r;
    }

    @Override
    public R save(OSSPo po) {
        return ExecuteUtils.getR(ossPoMapper.insert(po),"oss图片成功");
    }
}
