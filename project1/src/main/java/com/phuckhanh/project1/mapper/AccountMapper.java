package com.phuckhanh.project1.mapper;

import com.phuckhanh.project1.dto.request.AccountCreateRequest;
import com.phuckhanh.project1.dto.request.AccountUpdateRequest;
import com.phuckhanh.project1.dto.response.AccountResponse;
import com.phuckhanh.project1.entity.Account;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountCreateRequest accountCreateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccount(AccountUpdateRequest accountUpdateRequest, @MappingTarget Account account);

    AccountResponse toAccountResponse(Account account);
}
