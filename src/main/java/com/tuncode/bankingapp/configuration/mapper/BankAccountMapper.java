package com.tuncode.bankingapp.configuration.mapper;

import com.tuncode.bankingapp.configuration.response.BankAccountResponse;
import com.tuncode.bankingapp.configuration.response.TransactionDto;
import com.tuncode.bankingapp.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface BankAccountMapper {

    BankAccountMapper MAPPER = Mappers.getMapper(BankAccountMapper.class);

    BankAccountResponse mapToBankAccountResponseDto(BankAccount bankAccount,
                                                    List<TransactionDto> transactions);

}
