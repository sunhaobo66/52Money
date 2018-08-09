package com.hao.controller;

import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.core.vo.R;
import com.hao.core.vo.RM;
import com.hao.domain.admin.SysMenu;
import com.hao.domain.admin.SysUser;
import com.hao.service.admin.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/{page}")
    public String index(@PathVariable String page) {
        return "sys/menu/" +  page;
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions({"sys:menu:list"})
    public DataGridResult getPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return sysMenuService.getPageList(query);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions({"sys:menu:delete"})
    public R deleteBatch(@RequestBody Long[] ids) {
        sysMenuService.deleteBatch(ids);
        return R.setOK("批量删除成功");
    }

    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions({"sys:menu:save"})
    public R save(@RequestBody SysMenu menu) {
        sysMenuService.save(menu);
        return R.setOK("新增成功");
    }

    @RequestMapping("/info/{menuId}")
    @ResponseBody
    @RequiresPermissions({"sys:menu:info"})
    public RM save(@PathVariable Long menuId) {
        SysMenu sysMenu = sysMenuService.getById(menuId);
        return RM.ok().put("menu", sysMenu);
        // return new R(0,"菜单详情", sysMenu);
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions({"sys:menu:update"})
    public R update(@RequestBody SysMenu menu) {
        sysMenuService.update(menu);
        return R.setOK("更新成功");
    }

    @RequestMapping("/select")
    @ResponseBody
    @RequiresPermissions({"sys:menu:select"})
    public RM getPage() {
        List<SysMenu> list = sysMenuService.getNotButtonMenuList();
        return RM.ok().put("menuList", list);
        //return new R(0,"选择", list);
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/select_all1")
    @RequiresPermissions({"sys:menu:perms"})
    @ResponseBody
    public RM all(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.findAll();
        return RM.ok().put("menuList", menuList);
        // return new R(0,"授权列表", menuList);
   }


    @RequestMapping("/menu")
    @ResponseBody
    public RM menu() {
        //不知道是否正确
       SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("sysuser");
        System.out.println("id----"+user.getUserId());
        List<SysMenu> menuList = sysMenuService.findUserMenuList(user.getUserId());
        return RM.ok().put("menuList", menuList);
        //return new R(0,"菜单列表", menuList);
    }
}
