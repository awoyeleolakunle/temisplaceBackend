package com.dansaki.com.temisplacebackend.services.unitDashBoard.dashBoardDetails;

import com.dansaki.com.temisplacebackend.data.enums.ItemCategory;

import com.dansaki.com.temisplacebackend.data.models.Item;
import com.dansaki.com.temisplacebackend.data.models.OrderItemDetails;
import com.dansaki.com.temisplacebackend.data.models.Orders;
import com.dansaki.com.temisplacebackend.dtos.response.ItemCategoryInfoForUnitDashBoard;
import com.dansaki.com.temisplacebackend.services.item.itemService.ItemService;
import com.dansaki.com.temisplacebackend.services.orders.orderService.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UnitDashBoardDetailServiceImp implements UnitDashBoardDetailsService{
    private final ItemService itemService;
    private final OrderService orderService;
    @Override
    public List<ItemCategoryInfoForUnitDashBoard> fetchItemsDetailList(String unitName) {

        List<Orders> allCompletedOrdersForPreviousMonth = orderService.findAllUnitCompletedOrdersForThePreviousMonth(unitName);
        List<Orders> allCompletedOrdersForCurrentMonth = orderService.findAllUnitCompletedOrdersForTheCurrentMonth(unitName);
        List<Item> allItems = itemService.findAllItems();


        System.out.println("I'm the size of the completed orders for London unit in the previous month " + allCompletedOrdersForPreviousMonth.size());

        System.out.println("I'm the size of the completed orders for London unit in the current month " + allCompletedOrdersForCurrentMonth.size());
        List<ItemCategoryInfoForUnitDashBoard> listOfItemCategoryInfoForUnitDashBoard = new ArrayList<>();

  //      Map<String, Long> itemCategory = allItems.stream().collect(Collectors.groupingBy(item -> item.getItemCategory().name(), TreeMap::new, Collectors.counting()));

        Map<ItemCategory, List<OrderItemDetails>> ordersByCategoryPreviousMonth = allCompletedOrdersForPreviousMonth.stream()
                .flatMap(order -> order.getOrderItemDetailsList().stream())
                .collect(Collectors.groupingBy(
                        orderItemDetails -> orderItemDetails.getItem().getItemCategory(),
                        Collectors.toList()
                ));

        System.out.println("i'm the ordersByPrevious month's size " + ordersByCategoryPreviousMonth.size());

        Map<ItemCategory, List<OrderItemDetails>> ordersByCategoryCurrentMonth = allCompletedOrdersForCurrentMonth.stream()
                .flatMap(order -> order.getOrderItemDetailsList().stream())
                .collect(Collectors.groupingBy(
                        orderItemDetails -> orderItemDetails.getItem().getItemCategory(),
                        Collectors.toList()
                ));

        System.out.println("i'm the orders for current month's size " + ordersByCategoryCurrentMonth.size());


         List<Item> listOfItemsAvailableInUnit = allItems.stream()
                 .filter(item -> item.getListOfUnitsAvailable().contains(unitName)).toList();

//        Map<ItemCategory, List<Item>> itemCategoryListMap = allItems.stream().collect(Collectors.groupingBy(Item ::getItemCategory));
  //      System.out.println("I'm the itemCategoryListMap Size "  + itemCategoryListMap.size());

        //for (Map.Entry<ItemCategory, List<Item>>  eachItemEntryList : itemCategoryListMap.entrySet()) {

          //  ItemCategory itemCategory = eachItemEntryList.getKey();
           // List<Item> itemsInCategory = eachItemEntryList.getValue();
           // System.out.println("I'm the itemsInCategory's size " + itemsInCategory.size());
           // int totalItems = itemsInCategory.size();




//            long totalCountOfPreviousMonthCompletedOrders = allCompletedOrdersForPreviousMonth.stream().filter(itemsInCategory::contains).count();
//            System.out.println("I'm the total count of previous month " + totalCountOfPreviousMonthCompletedOrders);
//            long totalCountOfCurrentMonthCompletedOrders = allCompletedOrdersForCurrentMonth.stream().filter(itemsInCategory::contains).count();
//            System.out.println("I'm the total count of current month "+ totalCountOfCurrentMonthCompletedOrders);
//
//            double percentageDifferenceInMonthlySales =totalCountOfCurrentMonthCompletedOrders  == 0 ? 0 :
//                    (double) totalCountOfPreviousMonthCompletedOrders  / totalCountOfCurrentMonthCompletedOrders * 100;
//
//            System.out.println("I'm the percentage difference " + percentageDifferenceInMonthlySales);
//
//            ItemCategoryInfoForUnitDashBoard itemCategoryInfoForUnitDashBoard = new ItemCategoryInfoForUnitDashBoard();
//            itemCategoryInfoForUnitDashBoard.setNumberOfItems(totalItems);
//            itemCategoryInfoForUnitDashBoard.setCategory(itemCategory.toString());
//            itemCategoryInfoForUnitDashBoard.setPercentageDifferenceInMonthlySales(percentageDifferenceInMonthlySales);
//
//            listOfItemCategoryInfoForUnitDashBoard.add(itemCategoryInfoForUnitDashBoard);


        for (ItemCategory itemCategory : ItemCategory.values()) {
            List<OrderItemDetails> previousMonthOrders = ordersByCategoryPreviousMonth.getOrDefault(itemCategory, Collections.emptyList());
            List<OrderItemDetails> currentMonthOrders = ordersByCategoryCurrentMonth.getOrDefault(itemCategory, Collections.emptyList());

//            int totalItems = allItems.stream()
//                    .filter(item -> item.getItemCategory() == itemCategory)
//                    .mapToInt(item -> 1)
//                    .sum();

            int totalNumberOfAvailableItemsUnderEachCategoryInUnit = listOfItemsAvailableInUnit.stream()
                    .filter(item -> item.getItemCategory()== itemCategory)
                    .mapToInt(item->1)
                    .sum();

            long totalCountOfPreviousMonthCompletedOrders = previousMonthOrders.size();
            System.out.println("i'm the totalCountOfPreviousMonthCompletedOrders " + totalCountOfPreviousMonthCompletedOrders);

            long totalCountOfCurrentMonthCompletedOrders = currentMonthOrders.size();
            System.out.println("i'm the totalCountOfCurrentMonthCompletedOrders " + totalCountOfCurrentMonthCompletedOrders);

            double percentageDifferenceInMonthlySales = totalCountOfCurrentMonthCompletedOrders == 0 ? 0.0 :
                    ((double) (totalCountOfCurrentMonthCompletedOrders - totalCountOfPreviousMonthCompletedOrders) / totalCountOfCurrentMonthCompletedOrders) * 100.0;

            System.out.println("i'm the percentage difference in monthly sales " + percentageDifferenceInMonthlySales);
            ItemCategoryInfoForUnitDashBoard itemCategoryInfoForUnitDashBoard = new ItemCategoryInfoForUnitDashBoard();
            itemCategoryInfoForUnitDashBoard.setNumberOfItems(totalNumberOfAvailableItemsUnderEachCategoryInUnit);
            itemCategoryInfoForUnitDashBoard.setCategory(itemCategory.toString());
            itemCategoryInfoForUnitDashBoard.setPercentageDifferenceInMonthlySales(percentageDifferenceInMonthlySales);

            listOfItemCategoryInfoForUnitDashBoard.add(itemCategoryInfoForUnitDashBoard);

//            System.out.println(listOfItemCategoryInfoForUnitDashBoard.size());
//            System.out.println(listOfItemCategoryInfoForUnitDashBoard.get(5).getCategory());
//            System.out.println(listOfItemCategoryInfoForUnitDashBoard.get(5).getNumberOfItems());
//            System.out.println(listOfItemCategoryInfoForUnitDashBoard.get(5).getPercentageDifferenceInMonthlySales());

        }

        System.out.println(listOfItemCategoryInfoForUnitDashBoard.get(0).getCategory());
        System.out.println(listOfItemCategoryInfoForUnitDashBoard.get(0).getNumberOfItems());
        System.out.println(listOfItemCategoryInfoForUnitDashBoard.get(0).getPercentageDifferenceInMonthlySales());
        return listOfItemCategoryInfoForUnitDashBoard;

    }
}
