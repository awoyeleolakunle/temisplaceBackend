package com.dansaki.com.temisplacebackend.services.revenue.totalRevenue;

import com.dansaki.com.temisplacebackend.dtos.response.UnitMonthlyRevenueResponse;

import java.math.BigDecimal;
import java.util.List;

public interface TotalMonthlyAndDailyRevenueService {

    BigDecimal calculateDailyRevenue();

    BigDecimal calculatePreviousDayRevenue();
    List<UnitMonthlyRevenueResponse> calculateEachUnitMonthlyRevenueAndPutAllOfThemInAList();

}
