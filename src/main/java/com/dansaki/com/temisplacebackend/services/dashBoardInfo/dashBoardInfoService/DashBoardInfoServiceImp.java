package com.dansaki.com.temisplacebackend.services.dashBoardInfo.dashBoardInfoService;


import com.dansaki.com.temisplacebackend.data.enums.OrderFrom;
import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.response.DashBoardInfoResponse;
import com.dansaki.com.temisplacebackend.dtos.response.UnitMonthlyRevenueResponse;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import com.dansaki.com.temisplacebackend.services.revenue.totalRevenue.TotalMonthlyAndDailyRevenueService;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import com.dansaki.com.temisplacebackend.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DashBoardInfoServiceImp implements DashBoardInfoService {

    private final UserService userService;
    private final OrderService orderService;

    private final TotalMonthlyAndDailyRevenueService totalMonthlyAndDailyRevenueService;
    @Override
    public ApiResponse
    fetchDashBoardInfo() {


        List<User> listOfTodayRegisterCustomer = userService.findAllRegisteredCustomerForAParticularDate(LocalDate.now());
        Long totalRegisterCustomersForToday = (long)listOfTodayRegisterCustomer.size();
        List<User> listOfPreviousDayRegisteredCustomer = userService.findAllRegisteredCustomerForAParticularDate(LocalDate.now().minusDays(1));

       List<Orders> listOfCompletedOrders = orderService.findAllTodayOrdersByOrdertStatus(OrderStatus.COMPLETED);
       Long totalCompletedOrders = (long) listOfCompletedOrders.size();

       List<Orders> listOfCancelledOrders = orderService.findAllTodayOrdersByOrdertStatus(OrderStatus.CANCELLED);
       Long totalCancelledOrders = (long) listOfCancelledOrders.size();

       List<Orders> listOfInstoreOrders = orderService.findAllTodayOrdersUnderOrderFrom(OrderFrom.INSTORE);
       Long totalInstoreOrders = (long) listOfInstoreOrders.size();

       List<Orders> listOfOnlineOrders = orderService.findAllTodayOrdersUnderOrderFrom(OrderFrom.ONLINE);
        Long totalOnlineOrders = (long) listOfOnlineOrders.size();

        BigDecimal totalRevenue = totalMonthlyAndDailyRevenueService.calculateDailyRevenue();

        List<UnitMonthlyRevenueResponse> listOfAllUnitsWithMonthlyRevenue = totalMonthlyAndDailyRevenueService.calculateEachUnitMonthlyRevenueAndPutAllOfThemInAList();

        System.out.println("YEs Yes "+ totalRevenue);

        DashBoardInfoResponse dashBoardInfoResponse = mapDashBoardInfo(totalCompletedOrders, totalRegisterCustomersForToday, totalCancelledOrders, totalInstoreOrders, totalOnlineOrders, totalRevenue, listOfAllUnitsWithMonthlyRevenue );
        System.out.println("I'm the total registered customer" + dashBoardInfoResponse.getTotalCustomers());
        System.out.println("I'm the total completed orders " + dashBoardInfoResponse.getCompletedOrders());
        System.out.println("I'm the total cancelled orders " + dashBoardInfoResponse.getCancelledOrders());
        System.out.println("I'm the total revenue " + dashBoardInfoResponse.getTotalRevenue());
        return GenerateApiResponse.dashBoardInfo(dashBoardInfoResponse);
    }



    private DashBoardInfoResponse mapDashBoardInfo(Long totalCompletedOrders,  Long totalRegisterCustomersForToday, Long totalCancelledOrders, Long totalInstoreOrders, Long totalOnlineOrders, BigDecimal totalRevenue, List<UnitMonthlyRevenueResponse> listOfAllUnitsWithMonthlyRevenue){
        DashBoardInfoResponse dashBoardInfoResponse = new DashBoardInfoResponse();
        dashBoardInfoResponse.setCompletedOrders(totalCompletedOrders);
        dashBoardInfoResponse.setTotalCustomers(totalRegisterCustomersForToday);
        dashBoardInfoResponse.setCancelledOrders(totalCancelledOrders);
        dashBoardInfoResponse.setTotalRevenue(totalRevenue);
        dashBoardInfoResponse.setInstoreOrders(totalInstoreOrders);
        dashBoardInfoResponse.setOnlineOrders(totalOnlineOrders);
        dashBoardInfoResponse.setListOfAllUnitsWithMonthlyRevenue(new ArrayList<>(listOfAllUnitsWithMonthlyRevenue));
        dashBoardInfoResponse.setCompletedPercentage(calculatePercentageDifferenceForCompletedOrdersBetweenPreviousDayAndToday());
        dashBoardInfoResponse.setCancelledPercentage(calculatePercentageDifferenceForCancelledOrdersBetweenPreviousDayAndToday());
        dashBoardInfoResponse.setActivePercentage(calculatePercentageDifferenceForActiveOrdersBetweenPreviousDayAndToday());
        dashBoardInfoResponse.setInstorePercentage(calculatePercentageDifferenceForInstoreOrdersBetweenPreviousDayAndToday());
        dashBoardInfoResponse.setOnlinePercentage(calculatePercentageDifferenceForOnlineOrdersBetweenPreviousDayAndToday());
        dashBoardInfoResponse.setRevenuePercentage(calculatePercentageDifferenceForDailyRevenueBetweenPreviousDayAndToday());
        return dashBoardInfoResponse;
    }


    private Double calculatePercentageDifferenceForDailyRevenueBetweenPreviousDayAndToday(){
        BigDecimal todayRevenue = totalMonthlyAndDailyRevenueService.calculateDailyRevenue();
        BigDecimal previousDayRevenue = totalMonthlyAndDailyRevenueService.calculatePreviousDayRevenue();
        return todayRevenue.doubleValue()== 0 ? 0.0 :
                ( ((todayRevenue.subtract(previousDayRevenue) ).divide(todayRevenue, 2, RoundingMode.HALF_DOWN)).multiply(BigDecimal.valueOf(100.0))).doubleValue();
    }
    private Double calculatePercentageDifferenceForInstoreOrdersBetweenPreviousDayAndToday(){
            List<Orders> listOfAllTodayCompletedOrders = orderService.findAllTodayOrdersUnderOrderFrom(OrderFrom.INSTORE);
            List<Orders> listOfAllPreviousDayCompletedOrders = orderService.findAllPreviousDayOrdersUnderOrderFrom(OrderFrom.INSTORE);

        return listOfAllTodayCompletedOrders.size() == 0 ? 0.0 :
            ((double) (listOfAllTodayCompletedOrders.size() - listOfAllPreviousDayCompletedOrders.size() ) / listOfAllTodayCompletedOrders.size()) * 100.0;
    }

    private Double calculatePercentageDifferenceForOnlineOrdersBetweenPreviousDayAndToday(){
        List<Orders> listOfAllTodayCompletedOrders = orderService.findAllTodayOrdersUnderOrderFrom(OrderFrom.ONLINE);
        List<Orders> listOfAllPreviousDayCompletedOrders = orderService.findAllPreviousDayOrdersUnderOrderFrom(OrderFrom.ONLINE);
        return listOfAllTodayCompletedOrders.size()  == 0 ? 0.0 :
                ((double) listOfAllTodayCompletedOrders.size() - listOfAllPreviousDayCompletedOrders.size() / listOfAllTodayCompletedOrders.size())*100.0;
    }
    private Double calculatePercentageDifferenceForCompletedOrdersBetweenPreviousDayAndToday(){
        List<Orders> listOfCompletedOrdersForToday = orderService.findAllTodayOrdersByOrdertStatus(OrderStatus.COMPLETED);
        List<Orders> listOfCompletedOrdersForPreviousDay = orderService.findAllPreviousDayOrdersByOrderStatus(OrderStatus.COMPLETED);
        return listOfCompletedOrdersForToday.size() == 0 ? 0.0 :
                ((double) listOfCompletedOrdersForToday.size() - listOfCompletedOrdersForPreviousDay.size() / listOfCompletedOrdersForToday.size())*100.0;
    }
    private Double calculatePercentageDifferenceForCancelledOrdersBetweenPreviousDayAndToday(){
        List<Orders> listOfCancelledOrdersForToday = orderService.findAllTodayOrdersByOrdertStatus(OrderStatus.CANCELLED);
        List<Orders> listOfCancelledOrdersForPreviousDay = orderService.findAllPreviousDayOrdersByOrderStatus(OrderStatus.CANCELLED);
        return listOfCancelledOrdersForToday.size() == 0 ? 0.0 :
                ((double) listOfCancelledOrdersForToday.size() - listOfCancelledOrdersForPreviousDay.size() / listOfCancelledOrdersForToday.size())*100.0;
    }


    private Double calculatePercentageDifferenceForActiveOrdersBetweenPreviousDayAndToday(){
        List<Orders> listOfActiveOrdersForToday = orderService.findAllTodayOrdersByOrdertStatus(OrderStatus.ACTIVE);
        List<Orders> listOfActiveOrdersForPreviousDay = orderService.findAllPreviousDayOrdersByOrderStatus(OrderStatus.ACTIVE);
        return listOfActiveOrdersForToday.size() == 0 ? 0.0 :
                ((double) listOfActiveOrdersForToday.size() - listOfActiveOrdersForPreviousDay.size() / listOfActiveOrdersForToday.size())*100.0;
    }
}
