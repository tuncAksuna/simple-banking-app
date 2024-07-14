package com.tuncode.bankingapp.configuration.mapper;

import com.tuncode.bankingapp.configuration.response.TransactionDto;
import com.tuncode.bankingapp.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    List<TransactionDto> mapToTransactionDto(List<Transaction> transaction);
}
