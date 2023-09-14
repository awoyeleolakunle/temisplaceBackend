package com.dansaki.com.temisplacebackend.data.repositories;

import com.dansaki.com.temisplacebackend.data.models.OrderItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDetailsRepository extends JpaRepository<OrderItemDetails, Long> {
}
