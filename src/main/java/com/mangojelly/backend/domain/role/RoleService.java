package com.mangojelly.backend.domain.role;

import com.mangojelly.backend.domain.script.Script;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
