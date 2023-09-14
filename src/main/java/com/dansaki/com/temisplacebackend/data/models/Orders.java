package com.dansaki.com.temisplacebackend.data.models;


import com.dansaki.com.temisplacebackend.data.enums.OrderStatus;
import com.dansaki.com.temisplacebackend.data.enums.UnitName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @OneToMany
    @JoinColumn(name = "order_Id")
    private List<OrderItemDetails> orderItemDetailsList;
    private BigDecimal total = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private UnitName unitName;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime orderedTime;
}

