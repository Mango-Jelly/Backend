package com.mangojelly.backend.global.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PythonRunComponent {
    @Value("classpath:static/util/VideoEditor.py")
    private Resource pyFile;
}
