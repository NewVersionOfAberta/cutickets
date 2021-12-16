package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.ExpensesDto;
import com.natali.cultickets.model.Expenses;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpensesMapper {
    ExpensesDto expsToexpsDto(Expenses expenses);
}
