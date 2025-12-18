package com.sssys.grn.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sssys.grn.system.entity.SysUser;
import com.sssys.grn.system.mapper.SysUserMapper;
import com.sssys.grn.system.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
