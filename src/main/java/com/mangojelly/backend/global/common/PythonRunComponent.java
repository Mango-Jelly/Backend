package com.mangojelly.backend.global.common;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class PythonRunComponent {
    // outfile의 경로 + filename & 합칠 video의 경로 + videoName * video 이름
    public boolean runPy(List<String> commandTest) {

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commandTest);

            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
            processBuilder.directory(new File("C:\\Users\\SSAFY\\Desktop\\util"));
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if(exitCode == 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
