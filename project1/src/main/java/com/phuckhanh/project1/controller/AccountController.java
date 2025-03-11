package com.phuckhanh.project1.controller;

import com.phuckhanh.project1.dto.request.AccountCreateRequest;
import com.phuckhanh.project1.dto.request.AccountUpdateRequest;
import com.phuckhanh.project1.dto.response.AccountResponse;
import com.phuckhanh.project1.dto.response.ApiResponse;
import com.phuckhanh.project1.service.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @GetMapping("/all")
    public ApiResponse<List<AccountResponse>> getAllAccounts() {
        ApiResponse<List<AccountResponse>> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.getAllAccounts());

        return apiResponse;
    }

    @GetMapping
    public ApiResponse<AccountResponse> getAccount(@RequestParam(value = "id", required = false) String id,
                                                   @RequestParam(value = "username", required = false) String username) {
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();

        if (id != null) {
            apiResponse.setResult(accountService.getAccountById(id));
        } else if (username != null) {
            apiResponse.setResult(accountService.getAccountByUsername(username));
        }

        return apiResponse;
    }

    @GetMapping("/my-profile")
    public ApiResponse<AccountResponse> getMyAccount() {
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.getMyAccount());

        return apiResponse;
    }

    @PostMapping
    public ApiResponse<AccountResponse> createAccount(@RequestBody @Valid AccountCreateRequest accountCreateRequest) {
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.createAccount(accountCreateRequest));

        return apiResponse;
    }

    @PatchMapping("/{id}")
    public ApiResponse<AccountResponse> updateAccount(@PathVariable("id") String id, @RequestBody @Valid AccountUpdateRequest accountUpdateRequest) {
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.updateAccount(id, accountUpdateRequest));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAccount(@PathVariable("id") String id) {
        accountService.deleteAccount(id);

        return new ApiResponse<>();
    }
}
