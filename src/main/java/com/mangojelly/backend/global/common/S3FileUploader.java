package com.mangojelly.backend.global.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class S3FileUploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public PutObjectResult uploadFile(String filePath, String path) {
        try{
            ClassPathResource resource = new ClassPathResource(filePath);
            return amazonS3Client.putObject(bucket,path+"/"+resource.getFilename(),resource.getFile());
        }catch (Exception e){
            throw BusinessException.of(ErrorCode.API_ERROR_INTERNAL_SERVER);
        }
    }

    public PutObjectResult uploadFile(MultipartFile file, String path){
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            return amazonS3Client.putObject(bucket,path+file.getOriginalFilename(),file.getInputStream(),metadata);
        }catch (Exception e){
            throw BusinessException.of(ErrorCode.API_ERROR_INTERNAL_SERVER);
        }
    }

}
