package com.mangojelly.backend.domain.role;

import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Role extends BaseEntity {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id",nullable = false)
    private Script script;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Builder
    public Role(Script script, String name, String image){
        this.name = name;
        this.image = image;
        addRelatedByScript(script);
    }
    private void addRelatedByScript(Script script){
        this.script = script;
        script.getRoleList().add(this);
    }
}
