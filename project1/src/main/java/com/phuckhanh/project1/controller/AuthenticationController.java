package com.phuckhanh.project1.controller;

import com.nimbusds.jose.JOSEException;
import com.phuckhanh.project1.dto.request.IntrospectTokenRequest;
import com.phuckhanh.project1.dto.request.LoginRequest;
import com.phuckhanh.project1.dto.request.LogoutRequest;
import com.phuckhanh.project1.dto.request.RefreshTokenRequest;
import com.phuckhanh.project1.dto.response.ApiResponse;
import com.phuckhanh.project1.dto.response.IntrospectTokenResponse;
import com.phuckhanh.project1.dto.response.LoginResponse;
import com.phuckhanh.project1.entity.Account;
import com.phuckhanh.project1.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var result = authenticationService.login(loginRequest);

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(result);

        return apiResponse;
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);

        return new ApiResponse<>();
    }

    @PostMapping("/refreshToken")
    public ApiResponse<LoginResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(refreshTokenRequest);

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(result);

        return apiResponse;
    }

    @PostMapping("/introspectToken")
    public ApiResponse<IntrospectTokenResponse> refreshToken(@RequestBody IntrospectTokenRequest introspectTokenRequest) throws ParseException, JOSEException {
        var result = authenticationService.introspectToken(introspectTokenRequest);

        ApiResponse<IntrospectTokenResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(result);

        return apiResponse;
    }
}
