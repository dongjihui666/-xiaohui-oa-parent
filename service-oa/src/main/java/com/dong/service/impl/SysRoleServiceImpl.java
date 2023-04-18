package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.mapper.SysRoleMapper;
import com.dong.mapper.SysUserMapper;
import com.dong.mapper.SysUserRoleMapper;
import com.dong.model.system.SysRole;
import com.dong.model.system.SysUserRole;
import com.dong.service.SysRoleService;
import com.dong.vo.system.AssginRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService  {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> findRoleByAdminId(long userId) {
        // 查询所有角色
        List<SysRole> allRolesList = this.list();

        // 拥有的角色ID
        List<SysUserRole> existUserRoleList = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,userId).select(SysUserRole::getRoleId));
        List<Long> existRoleIdList = existUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());

        // 对角色进行分类
        List<SysRole> assginRoleList = new ArrayList<>();
        for(SysRole role : allRolesList){
            //已分配
            if (existRoleIdList.contains(role.getId())){
                assginRoleList.add(role);
            }
        }
        Map<String,Object> roleMap = new HashMap<>();
        roleMap.put("assginRoleList",assginRoleList);
        roleMap.put("allRoleList",allRolesList);
        return roleMap;

    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {

    }
}
