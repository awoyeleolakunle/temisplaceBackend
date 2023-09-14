package com.dansaki.com.temisplacebackend.services.dashBoardInfo.dashBoardInfoService;


import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import com.dansaki.com.temisplacebackend.dtos.response.DashBoardInfoResponse;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import com.dansaki.com.temisplacebackend.services.revenue.totalRevenue.TotalMonthlyRevenueService;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class DashBoardInfoServiceImp implements DashBoardInfoService {

    private final UserService userService;
    private final OrderService orderService;

    private final TotalMonthlyRevenueService totalMonthlyRevenueService;
    @Override
    public ApiResponse fetchDashBoardInfo() {


        List<User> registeredCustomers = userService.findAllRegisteredUsers();
        Long totalRegisteredCustomer = (long) registeredCustomers.size();

       List<Orders> listOfCompletedOrders = orderService.findTotalCompletedOrder();
       Long totalCompletedOrders = (long) listOfCompletedOrders.size();

       List<Orders> listOfCancelledOrders = orderService.findTotalCancelledOrders();
       Long totalCancelledOrders = (long) listOfCancelledOrders.size();

        BigDecimal totalRevenue = totalMonthlyRevenueService.calculateMonthlyRevenue();

        System.out.println("YEs Yes "+ totalRevenue);

        DashBoardInfoResponse dashBoardInfoResponse = mapDashBoardInfo(totalCompletedOrders, totalRegisteredCustomer, totalCancelledOrders, totalRevenue);
        System.out.println("I'm the total registered customer" + dashBoardInfoResponse.getTotalCustomers());
        System.out.println("I'm the total completed orders " + dashBoardInfoResponse.getCompletedOrders());
        System.out.println("I'm the total cancelled orders " + dashBoardInfoResponse.getCancelledOrders());
        System.out.println("I'm the total revenue " + dashBoardInfoResponse.getTotalRevenue());
        return GenerateApiResponse.dashBoardInfo(dashBoardInfoResponse);
    }



    private DashBoardInfoResponse mapDashBoardInfo(Long totalCompletedOrders,  Long totalRegisteredCustomer, Long totalCancelledOrders, BigDecimal totalRevenue){
        DashBoardInfoResponse dashBoardInfoResponse = new DashBoardInfoResponse();
        dashBoardInfoResponse.setCompletedOrders(totalCompletedOrders);
        dashBoardInfoResponse.setTotalCustomers(totalRegisteredCustomer);
        dashBoardInfoResponse.setCancelledOrders(totalCancelledOrders);
        dashBoardInfoResponse.setTotalRevenue(totalRevenue);
        return dashBoardInfoResponse;
    }
}
