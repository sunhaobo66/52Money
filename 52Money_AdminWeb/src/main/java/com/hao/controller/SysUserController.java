package com.hao.controller;

import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.core.vo.R;
import com.hao.core.vo.RM;
import com.hao.domain.admin.SysUser;
import com.hao.service.admin.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseController{
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/{page}")
    public String listPage(@PathVariable String page){
        System.out.println("pageUser:"+page);
        return "sys/user/"+page;
    }
    @RequestMapping("list")
    @ResponseBody
    @RequiresPermissions("{sys:user:list}")
    public DataGridResult getPage(@RequestParam Map<String,Object> params){
        //查询列表数据
        Query query = new Query(params);
        return sysUserService.getPageList(query);

    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("sys:user:delete")
    public R deleteMore(@RequestBody Long[] ids){
        //        if(ArrayUtils.contains(ids, 1L)){
//            return R.setError("系统管理员不能删除");
//        }
        sysUserService.deleteBatch(ids);
        return R.setOK("删除成功");
    }
    @RequestMapping("/info/{userId}")
    @ResponseBody
    @RequiresPermissions("sys:user:info")
    public RM info(@PathVariable Long userId){
        SysUser user = sysUserService.getById(userId);
        return RM.ok().put("user",user);
    }
    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUser user){
        sysUserService.save(user);
        return R.setOK("新增成功");
    }
    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions({"sys:user:update"})
    public R update(@RequestBody SysUser user){
        sysUserService.update(user);
        return R.setOK("修改成功");
    }
}
