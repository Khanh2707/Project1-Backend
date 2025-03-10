package com.phuckhanh.project1.mapper;

import com.phuckhanh.project1.dto.request.AccountCreateRequest;
import com.phuckhanh.project1.dto.response.AccountResponse;
import com.phuckhanh.project1.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountCreateRequest accountCreateRequest);

    AccountResponse toAccountResponse(Account account);
}
