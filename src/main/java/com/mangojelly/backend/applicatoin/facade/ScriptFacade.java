package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.response.*;
import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.role.RoleService;
import com.mangojelly.backend.domain.scenario.Scenario;
import com.mangojelly.backend.domain.scenario.ScenarioService;
import com.mangojelly.backend.domain.scene.Scene;
import com.mangojelly.backend.domain.scene.SceneService;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.domain.script.ScriptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScriptFacade {
    private final ScriptService scriptService;
    private final RoleService roleService;
    private final SceneService sceneService;
    private final ScenarioService scenarioService;

    /**
     * 스크립트 리스트 메소드
     */
    public GetAllScriptResponse getAllScripts() {
        List<Script> scripts = scriptService.findAll();
        return GetAllScriptResponse.of(scripts);
    }

    public ScriptDetailResponse getScript(int id) {
        Script script = scriptService.findById(id);

        List<SceneResponse> scenes = new ArrayList<>();
        List<DialogResponse> dialogs = new ArrayList<>();
        for(Scene scene : sceneService.findByScript(script)){
            for(Scenario scenario : scenarioService.findAllByScript(scene.getAddress())) {
                for(int i = 0; i < scenario.getScenario().size(); i++){
                    String[] scenarioSplit = scenario.getScenario().get(i).split(":");
                    List<SceneRoleResponse> sceneRoles = new ArrayList<>();
                    for(Role role : roleService.findByScriptAndName(script, scenarioSplit[0]))
                        sceneRoles.add(SceneRoleResponse.from(role));
                    dialogs.add(DialogResponse.of(sceneRoles, scenarioSplit[1]));
                }
            }
            scenes.add(SceneResponse.of(scene, dialogs));
        }

        List<RoleResponse> roles = new ArrayList<>();
        for(Role role : roleService.findByScript(script))
            roles.add(RoleResponse.from(role));

        return ScriptDetailResponse.of(scenes, script, roles);
    }
}
