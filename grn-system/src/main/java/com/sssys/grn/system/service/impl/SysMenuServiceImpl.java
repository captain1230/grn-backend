package com.sssys.grn.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sssys.grn.system.entity.SysMenu;
import com.sssys.grn.system.mapper.SysMenuMapper;
import com.sssys.grn.system.service.SysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
}
