package com.dong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.mapper.SysUserMapper;
import com.dong.model.system.SysUser;
import com.dong.service.SysRoleService;
import com.dong.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
