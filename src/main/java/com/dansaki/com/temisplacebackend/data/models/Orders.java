package com.dansaki.com.temisplacebackend.data.models;


import com.dansaki.com.temisplacebackend.data.enums.*;
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_Id")
    private List<OrderItemDetails> orderItemDetailsList;
    private BigDecimal total = BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private UnitName unitName;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private ActiveOrderStatus activeOrderStatus;
    @Enumerated(EnumType.STRING)
    private OrderFrom orderFrom;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private LocalDateTime orderedTime;
    private int numberOfAllItemQuantityOrdered;
}

