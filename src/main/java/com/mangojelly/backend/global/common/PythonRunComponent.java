package com.mangojelly.backend.global.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PythonRunComponent {
    // outfile의 경로 + filename & 합칠 video의 경로 + videoName * video 이름
    public boolean runPy(List<String> commandTest) throws Exception{
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("window");
        String python = isWindows ? "python" : "python3";
        commandTest.add(0,python);
        ProcessBuilder processBuilder = new ProcessBuilder(commandTest);

        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        ClassPathResource resource = new ClassPathResource("util");
        processBuilder.directory(resource.getFile());
        Process process = processBuilder.start();

        return process.waitFor() == 0;
    }
}
