package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public List<Role> findOtherRole(String id) throws Exception {
        return roleDao.findOtherRole(id);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleID) throws Exception {
        return roleDao.findOtherPermissions(roleID);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) throws Exception {
        for(String PermissionsId:ids){
            roleDao.addPermissionToRole(roleId,PermissionsId);
        }

    }
}
