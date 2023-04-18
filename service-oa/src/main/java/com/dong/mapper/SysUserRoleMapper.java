package com.dong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.model.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
