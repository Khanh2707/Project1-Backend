package com.phuckhanh.project1.controller;

import com.phuckhanh.project1.dto.request.AccountCreateRequest;
import com.phuckhanh.project1.dto.response.AccountResponse;
import com.phuckhanh.project1.dto.response.ApiResponse;
import com.phuckhanh.project1.service.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @GetMapping
    public ApiResponse<AccountResponse> getAccountById(@RequestParam("id") String id) {
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.getAccountById(id));

        return apiResponse;
    }

    @PostMapping
    public ApiResponse<AccountResponse> createAccount(@RequestBody @Valid AccountCreateRequest accountCreateRequest) {
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.createAccount(accountCreateRequest));

        return apiResponse;
    }
}
