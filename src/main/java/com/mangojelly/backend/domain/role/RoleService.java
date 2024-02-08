package com.mangojelly.backend.domain.role;

import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Transactional
    public Role save(Script script, String name, String image){
        return roleRepository.save(roleMapper.toEntity(script,name,image));
    }

    public Role findById(int id){
        return roleRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_ROLE_NOT_EXIST));
    }

    public Role findById(int id, Script script){
        return roleRepository.findByIdAndScript(id, script).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_ROLE_NOT_EXIST));
    }

    public List<Role> findByScript(Script script){
        List<Role> roles = roleRepository.findByScript(script);
        if(roles.isEmpty())
            throw BusinessException.of(ErrorCode.API_ERROR_ROLE_NOT_EXIST);
        return roles;
    }

    public List<Role> findByScriptAndName(Script script, String roleNames){
        List<Role> roles = new ArrayList<>();
        String[] roleArr = roleNames.split(",");

        for(String roleName : roleArr){
            Role role = roleRepository.findByScriptAndName(script, roleName.trim()).orElseThrow();
            roles.add(role);
        }
        return roles;
    }
}
