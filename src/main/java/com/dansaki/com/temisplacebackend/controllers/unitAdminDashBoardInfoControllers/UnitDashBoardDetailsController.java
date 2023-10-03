package com.dansaki.com.temisplacebackend.controllers.unitAdminDashBoardInfoControllers;


import com.dansaki.com.temisplacebackend.dtos.response.ItemCategoryInfoForUnitDashBoard;
import com.dansaki.com.temisplacebackend.services.unitDashBoard.dashBoardDetails.UnitDashBoardDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UnitDashBoardDetailsController {

    private final UnitDashBoardDetailsService unitDashBoardDetailsService;

    @PostMapping("unitDashboardDetails")
    public ResponseEntity<List<ItemCategoryInfoForUnitDashBoard>> fetchUnitDashboardDetails(@RequestParam String unitName){
        return new ResponseEntity<>(unitDashBoardDetailsService.fetchItemsDetailList(unitName), HttpStatus.OK);

    }
}
