package com.dong;

import com.dong.mapper.SysRoleMapper;
import com.dong.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemo1 {
    
    // 注入
    @Autowired
    private SysRoleMapper sysRoleMapper;

    // 查询所有记录
    @Test
    public void getAll() {

        for (SysRole sysRole : sysRoleMapper.selectList(null)) {
            System.out.println(sysRole);
        }

    }

    //添加操作
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");
        sysRoleMapper.insert(sysRole);
    }

    //修改操作
    @Test
    public void update() {
        // 根据id查询
        SysRole sysRole = sysRoleMapper.selectById(1);
        // 设置修改值

        // 调用方法实现最终修改
    }
}
