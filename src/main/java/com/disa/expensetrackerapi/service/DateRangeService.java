package com.disa.expensetrackerapi.service;

import java.time.LocalDate;

public interface DateRangeService {

    LocalDate getStartOfMonth(int year, int month);
    LocalDate getEndOfMonth(int year, int month);



}
