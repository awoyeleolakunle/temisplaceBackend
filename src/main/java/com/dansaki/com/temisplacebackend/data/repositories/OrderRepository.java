package com.dansaki.com.temisplacebackend.data.repositories;

import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByOrderStatus(OrderStatus orderStatus);
    List<Orders> findAllByUnitNameAndOrderStatus(UnitName unitName, OrderStatus orderStatus);
    List<Orders>
    findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName unitName,LocalDateTime startDayOfPreviousMonth,
                                                         LocalDateTime lastDayOfPreviousMonth, OrderStatus orderStatus );


            //findAllByOrderedTimeBetweenAndOrderStatus
            //(LocalDateTime startDayOfPreviousMonth, LocalDateTime lastDayOfPreviousMonth, OrderStatus orderStatus);

}
