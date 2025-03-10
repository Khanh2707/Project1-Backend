package com.phuckhanh.project1.service;

import com.phuckhanh.project1.dto.request.AccountCreateRequest;
import com.phuckhanh.project1.dto.request.AccountUpdateRequest;
import com.phuckhanh.project1.dto.response.AccountResponse;
import com.phuckhanh.project1.entity.Account;
import com.phuckhanh.project1.exception.AppException;
import com.phuckhanh.project1.exception.ErrorCode;
import com.phuckhanh.project1.mapper.AccountMapper;
import com.phuckhanh.project1.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {
    AccountRepository accountRepository;

    AccountMapper accountMapper;

    public AccountResponse getAccountById(String id) {
        return accountMapper.toAccountResponse(accountRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOTFOUND)));
    }

    public AccountResponse getAccountByUsername(String username) {
        return accountMapper.toAccountResponse(accountRepository.findByUsername(username)
                        .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOTFOUND)));
    }

    public AccountResponse getMyAccount() {
        var context = SecurityContextHolder.getContext();
        String usernameAccount = context.getAuthentication().getName();

        Account account = accountRepository.findByUsername(usernameAccount).orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOTFOUND));

        return accountMapper.toAccountResponse(account);
    }

    public AccountResponse createAccount(AccountCreateRequest accountCreateRequest) {
        if (accountRepository.existsByUsername(accountCreateRequest.getUsername())) {
            throw new AppException(ErrorCode.ACCOUNT_EXISTED);
        }

        Account account = accountMapper.toAccount(accountCreateRequest);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        account.setPassword(passwordEncoder.encode(accountCreateRequest.getPassword()));

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    public AccountResponse updateAccount(String id, AccountUpdateRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOTFOUND));

        accountMapper.updateAccount(request, account);

        accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }
}
