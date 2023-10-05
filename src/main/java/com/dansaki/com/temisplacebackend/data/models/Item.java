package com.dansaki.com.temisplacebackend.data.models;

import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;
import com.dansaki.com.temisplacebackend.data.enums.PublishingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String itemTitle;
    @Enumerated(EnumType.STRING)
    private ItemCategory itemCategory;
    private String ItemImgUrl;
    private List<String> ingredients;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemPriceAndSize> itemPriceAndSize;
    @Enumerated(EnumType.STRING)
    private PublishingType publishingType;
    private String allergy;
    private List<String> listOfUnitsAvailable;
    private int itemSize;
}
