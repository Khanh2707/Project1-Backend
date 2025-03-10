package com.phuckhanh.project1.service;

import com.phuckhanh.project1.dto.request.AccountCreateRequest;
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

    public AccountResponse createAccount(AccountCreateRequest accountCreateRequest) {
        if (accountRepository.existsByUsername(accountCreateRequest.getUsername())) {
            throw new AppException(ErrorCode.ACCOUNT_EXISTED);
        }

        Account account = accountMapper.toAccount(accountCreateRequest);

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }
}
