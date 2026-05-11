package com.disa.expensetrackerapi.service.impl;

import com.disa.expensetrackerapi.service.DateRangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class DateRangeServiceImpl implements DateRangeService {

    @Override
    public LocalDate getStartOfMonth(int year, int month) {
        return YearMonth.of(year, month).atDay(1);
    }

    @Override
    public LocalDate getEndOfMonth(int year, int month) {
        return YearMonth.of(year, month).atEndOfMonth();
    }
}
