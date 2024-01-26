package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.global.response.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mangojelly.backend.global.response.api.ResponseCode.API_SUCCESS_DOMAIN_METHOD;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    @Operation(summary = "테스트 API 요청")
    @GetMapping()
    public ResponseEntity<ApiResponse<Void>> hello(){
        return ResponseEntity.ok(new ApiResponse<>(API_SUCCESS_DOMAIN_METHOD));
    }
}
