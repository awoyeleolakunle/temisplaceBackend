package com.dansaki.com.temisplacebackend.services.orders.orderService;

import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor

public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    @Override
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> findTotalCompletedOrder() {
        return orderRepository.findAllByOrderStatus(OrderStatus.COMPLETED);
    }

    @Override
    public List<Orders> findTotalCancelledOrders() {
        return orderRepository.findAllByOrderStatus(OrderStatus.CANCELLED);
    }

    @Override
    public List<Orders> findAllLondonUnitCompletedOrders(UnitName unitName, OrderStatus orderStatus) {
        return orderRepository.findAllByUnitNameAndOrderStatus(unitName, orderStatus);
    }

    @Override
    public List<Orders> findAllUnitCompletedOrdersForThePreviousMonth(String unitName) {
        String unitNameInUpperCase = unitName.toUpperCase();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfCurrentMonth = currentDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime firstDayOfPreviousMonth = firstDayOfCurrentMonth.minusMonths(1);
        LocalDateTime lastDayOfPreviousMonth = firstDayOfCurrentMonth.minusSeconds(1);
        return orderRepository.findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName.valueOf(unitNameInUpperCase), firstDayOfPreviousMonth, lastDayOfPreviousMonth, OrderStatus.COMPLETED);
    }

    @Override
    public List<Orders> findAllUnitCompletedOrdersForTheCurrentMonth(String unitName) {
        String unitNameInUpperCase = unitName.toUpperCase();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfCurrentMonth = currentDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName.valueOf(unitNameInUpperCase), firstDayOfCurrentMonth, currentDateTime, OrderStatus.COMPLETED);
    }


    public List<Orders> findAUnitAllOrdersUnderOrderStatusForToday(String unitName, OrderStatus orderStatus) {
        LocalDateTime currentDateTime  = LocalDateTime.now();
        LocalDateTime startOfToday = currentDateTime.withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName.valueOf(unitName.toUpperCase()),
              startOfToday, currentDateTime, orderStatus );
    }

    @Override
    public List<Orders> findAUnitAllOrdersUnderOrderStatusForThePreviousDay(String unitName, OrderStatus orderStatus) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startOfToday =currentDateTime.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime startOfThePreviousDay = startOfToday.minusDays(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfThePreviousDay = startOfToday.minusSeconds(1);

        return orderRepository.findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName.valueOf(unitName.toUpperCase()),
                startOfThePreviousDay, endOfThePreviousDay, orderStatus);
    }

//    @Override
//    public List<Orders> findAUnitAllCancelledOrdersForToday(String unitName) {
//        LocalDateTime currentDateTime  = LocalDateTime.now();
//        LocalDateTime startOfToday = currentDateTime.withHour(0).withMinute(0).withSecond(0);
//
//        return orderRepository.findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName.valueOf(unitName),
//                startOfToday, currentDateTime, OrderStatus.CANCELLED);
//    }


}
