package com.dansaki.com.temisplacebackend.dtos.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnitOrderDashBoardDetailResponse {
    private BigDecimal revenue;
    private int completedOrder;
    private double completedPercentageDifference;
    private int activeOrder;
    private double activePercentageDifference;
    private int cancelledOrder;
    private double cancelledPercentageDifference;
}
