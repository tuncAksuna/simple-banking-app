package com.tuncode.bankingapp.configuration.mapper;

import com.tuncode.bankingapp.configuration.response.BankAccountResponse;
import com.tuncode.bankingapp.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountMapper MAPPER = Mappers.getMapper(BankAccountMapper.class);

    BankAccountResponse mapToBankAccountResponseDto(BankAccount bankAccount);

}
