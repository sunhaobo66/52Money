package com.hao.service.admin.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.hao.core.util.EncrypUtil;
import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.domain.admin.SysUserRole;
import com.hao.mapper.admin.SysUserMapper;
import com.hao.domain.admin.SysUser;
import com.hao.mapper.admin.SysUserRoleMapper;
import com.hao.service.admin.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private static final String SysUser = null;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public DataGridResult getPageList(Query query) {
        Integer offset = (Integer)query.get("offset");
        Integer limit = (Integer)query.get("limit");
        System.err.println("offset:"+offset);
        System.err.println("limit:"+limit);
        //调用Dao查询分页列表数据
        List<SysUser> rows = userMapper.selectByPage(offset, limit);
        for (SysUser sysUser : rows) {
            sysUser.setPassword(null);
        }
        //调用Dao查询总记录数
        Long total = (Long)userMapper.selectCount();
        //创建DataGridResult对象
        DataGridResult dataGridResult = new DataGridResult(rows, total);
        return dataGridResult;
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        userMapper.deleteBatch(userIds);
        userRoleMapper.deleteByUserIds(userIds);
    }

    @Override
    public SysUser getById(Long userId) {
        SysUser user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(null);//不返回密码
        //获取用户所属的角色列表
        List<Long> roleIdList = userRoleMapper.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return user;
    }

    @Override
    public void save(SysUser user) {

        //创建者id
        SysUser creator = (SysUser)SecurityUtils.getSubject().getPrincipal();
        user.setCreateUserId(creator.getUserId());

        user.setCreateTime(new Date());
        //md5加密
        user.setPassword(EncrypUtil.md5Pass(user.getPassword()));
        userMapper.insert(user);
        Long userId = user.getUserId();
        List<Long> roleIdList = user.getRoleIdList();
        if(roleIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        for (int i = 0; i < roleIdList.size(); i++) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleIdList.get(i));
            sysUserRole.setUserId(userId);
            userRoleMapper.insert(sysUserRole);
        }
    }

    @Override
    public void update(SysUser user) {
        //获取数据库中的密码
        String password = userMapper.queryPassword(user.getUserId());

        //如果用户没修改密码，则使用原来的密码
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(password);
        }else{
            user.setPassword(EncrypUtil.md5Pass(user.getPassword()));
        }
        userMapper.updateByPrimaryKey(user);

        //TODO 检查角色是否越权
        //checkRole(user);

        //先删除用户与角色关系
        Long userId = user.getUserId();
        userRoleMapper.deleteByUserId(userId);

        List<Long> roleIdList = user.getRoleIdList();
        if(roleIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        for (int i = 0; i < roleIdList.size(); i++) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleIdList.get(i));
            sysUserRole.setUserId(userId);
            userRoleMapper.insert(sysUserRole);
        }
    }

    @Override
    public SysUser getByUsername(String username) {
        return userMapper.queryByUserName(username);
    }
}
