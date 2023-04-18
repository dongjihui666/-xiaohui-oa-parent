package com.dong.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.Result;
import com.dong.ResultCodeEnum;
import com.dong.model.system.SysRole;
import com.dong.service.SysRoleService;
import com.dong.vo.system.AssginRoleVo;
import com.dong.vo.system.SysRoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
@CrossOrigin
public class SysRoleController {

    // 注入service
    @Autowired
    private SysRoleService sysRoleService;

    // 查询所有的角色
//    @GetMapping("/findAll")
//    public List<SysRole> findAll() {
//        // 调用service方法
//        List<SysRole> list = sysRoleService.list();
//        return list;
//    }

    // 查询所有的角色 统一返回数据的结果
    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result findAll() {
        List<SysRole> list = sysRoleService.list();

        return Result.ok(list);
    }

    /**
     * 条件分页查询
     * page 当前页 limit 每页显示记录数
     * SysRoleQueryVo  用户查询的实体类
     *
     * @param page
     * @param limit
     * @param sysRoleQueryVo
     * @return
     */
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole (@PathVariable Long page, @PathVariable Long limit,
                                 SysRoleQueryVo sysRoleQueryVo) {
        // 调用service的方法实现
        // 1 创建page对象,传递分页相关参数
        // page 当前页  limit 每页显示的记录数 SysRole 用户实体类
        Page<SysRole> rolePage = new Page<>(page, limit);

        //2 封装条件,判断条件是否为空,不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        // 根据用户查询实体的名字
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            // 封装like 模糊查询
            //jDK8中有双冒号的用法，就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下。
            //类名::方法名
            wrapper.like(SysRole::getRoleName,roleName);
        }
        // 3 调用方法实现
        Page<SysRole> sysRolePage = sysRoleService.page(rolePage, wrapper);
        return Result.ok(sysRolePage);
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole byId = sysRoleService.getById(id);
        return Result.ok(byId);
    }

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result updateById(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysRoleService.removeById(id);
        return Result.ok();
    }

    /**
     * 批量删除
     * @param idList
     * @return
     */
    @ApiOperation(value = "批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        sysRoleService.removeByIds(idList);
        return Result.ok("删除id成功");

    }
    /**
     * 给用户分配角色及更改用户状态
     *
     */
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable long userId) {
        Map<String,Object> roleMap = sysRoleService.findRoleByAdminId(userId);
        return Result.ok(roleMap);
    }
    /**
     * 根据用户分配角色
     */
    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }




}
