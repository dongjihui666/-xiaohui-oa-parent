package com.dong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.model.system.SysRole;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * SysRole实体类操作
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
