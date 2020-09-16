package com.business.sys.service;

import com.business.sys.dto.SysMenuDto;

import java.util.List;

public interface ISysMenuService {

    public List<SysMenuDto> getAuthMenu(String username);
}
