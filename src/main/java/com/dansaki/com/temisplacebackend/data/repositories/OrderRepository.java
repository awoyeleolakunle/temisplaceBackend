package com.dansaki.com.temisplacebackend.data.repositories;

import com.dansaki.com.temisplacebackend.data.enums.OrderFrom;
import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByOrderStatus(OrderStatus orderStatus);

    List<Orders> findAllByOrderedTimeBetweenAndOrderStatus(LocalDateTime startOfCurrentMonthOrDay, LocalDateTime currentTime, OrderStatus orderStatus);

   List<Orders> findAllByOrderedTimeBetweenAndOrderFrom(LocalDateTime startOfCurrentMonth, LocalDateTime currentTime, OrderFrom orderFrom);
    List<Orders> findAllByUnitNameAndOrderStatus(UnitName unitName, OrderStatus orderStatus);
    List<Orders>
    findAllByUnitNameAndOrderedTimeBetweenAndOrderStatus(UnitName unitName,LocalDateTime startDayOfPreviousMonth,
                                                         LocalDateTime lastDayOfPreviousMonth, OrderStatus orderStatus );

                   //findAllByOrderedTimeBetweenAndOrderStatus
            //(LocalDateTime startDayOfPreviousMonth, LocalDateTime lastDayOfPreviousMonth, OrderStatus orderStatus);

}
