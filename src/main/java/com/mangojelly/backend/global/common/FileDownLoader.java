package com.mangojelly.backend.global.common;

import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileDownLoader {

    private final DefaultResourceLoader resourceLoader;


    public void loadFile(List<String> urls, String path) {
        for (String url : urls){
            loadFile(url,path);
        }
    }

    public void loadFile(MultipartFile multipartFile, String path){
        try{
            getInputStream(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), path);
        }catch (IOException e){
            e.printStackTrace();
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
        }
    }

    public void loadFile(String url,String path) {
        Resource resource = resourceLoader.getResource(url);
        try{
            getInputStream(resource.getInputStream(), resource.getFilename(),path);
        }catch (IOException e){
            e.printStackTrace();
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
        }
    }


    private void getInputStream(InputStream inputStream, String fileName,String path) throws IOException {
//        String filePath = resourceLoader.getResource("classpath:util/VideoConcater.py").getFile().toPath().toString();
        String filePath = new ClassPathResource("util/VideoConcater.py").getFile().toPath().toString();
        String rootPath = filePath.substring(0,filePath.length()-"util/VideoConcater.py".length());
        Path destinationPath = Path.of(rootPath+path+fileName);

        // 파일 저장
        try (FileOutputStream outputStream = new FileOutputStream(destinationPath.toFile())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        inputStream.close();
    }
}
