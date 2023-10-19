package com.dansaki.com.temisplacebackend.services.orders.orderService;

import com.dansaki.com.temisplacebackend.data.enums.OrderFrom;
import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.data.repositories.OrderRepository;
import com.dansaki.com.temisplacebackend.dtos.request.UnitOrderUnderOrderStatusRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    @Override
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> findTotalMonthlyCompletedOrder() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfCurrentMonth = currentDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByOrderedTimeBetweenAndOrderStatus(firstDayOfCurrentMonth, currentDateTime, OrderStatus.COMPLETED);
    }

    @Override
    public Optional<Orders> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Orders> findTotalMonthlyCancelledOrders() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfCurrentMonth = currentDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByOrderedTimeBetweenAndOrderStatus(firstDayOfCurrentMonth, currentDateTime, OrderStatus.CANCELLED);
    }

    @Override
    public List<Orders> findTotalMonthlyInstoreOrders() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfCurrentMonth = currentDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByOrderedTimeBetweenAndOrderFrom(firstDayOfCurrentMonth, currentDateTime, OrderFrom.INSTORE);
    }

    @Override
    public List<Orders> findTotalMonthlyOnlineOrders() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime firstDayOfCurrentMonth = currentDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByOrderedTimeBetweenAndOrderFrom(firstDayOfCurrentMonth, currentDateTime, OrderFrom.ONLINE);
    }

    @Override
    public List<Orders> findAllTodayOrdersByOrdertStatus(OrderStatus orderStatus) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfToday = currentTime.withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByOrderedTimeBetweenAndOrderStatus(startOfToday, currentTime, orderStatus);
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

    @Override
    public List<Orders> findAUnitAllDailyOrdersUnderOrderStatus(UnitOrderUnderOrderStatusRequest unitOrderUnderOrderStatusRequest) {

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfCurrentDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        List<Orders> list = orderRepository.findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName.valueOf(unitOrderUnderOrderStatusRequest.getUnitName().toUpperCase()),
                startOfCurrentDay, currentTime,  OrderStatus.valueOf(unitOrderUnderOrderStatusRequest.getOrderStatus()));

        if(list.isEmpty()){return list;}

        System.out.println("I'm the size of the list " + list.size());

        return list;
    }

    @Override
    public List<Orders> findAUnitMonthlyOrdersUnderOrderStatus(String unitName, OrderStatus orderStatus) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime firstDayOfCurrentMonth = currentTime.withDayOfMonth(1).withHour(0).withMinute(0);

        return orderRepository.findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName.valueOf(unitName), firstDayOfCurrentMonth, currentTime, orderStatus) ;
    }

    @Override
    public List<Orders> findAllTodayOrdersUnderOrderFrom(OrderFrom orderFrom) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfCurrentDay = currentTime.withHour(0).withMinute(0).withSecond(0);
        return orderRepository.findAllByOrderedTimeBetweenAndOrderFrom(startOfCurrentDay, currentTime, orderFrom);
    }

    @Override
    public List<Orders> findAllPreviousDayOrdersUnderOrderFrom(OrderFrom orderFrom) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfToday = currentTime.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime startOfPreviousDay = startOfToday.minusDays(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfPreviousDay = startOfToday.minusSeconds(1);

        return orderRepository.findAllByOrderedTimeBetweenAndOrderFrom(startOfPreviousDay, endOfPreviousDay, orderFrom);
    }

    @Override
    public List<Orders> findAllPreviousDayOrdersByOrderStatus(OrderStatus orderStatus) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfToday = currentTime.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime startOfPreviousDay = startOfToday.minusDays(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfPreviousDay = startOfToday.minusSeconds(1);
        return orderRepository.findAllByOrderedTimeBetweenAndOrderStatus(startOfPreviousDay, endOfPreviousDay, orderStatus);
    }
}
