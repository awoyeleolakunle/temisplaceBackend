package com.dansaki.com.temisplacebackend.controllers.unitOrderDashBoardController;


import com.dansaki.com.temisplacebackend.dtos.response.UnitOrderDashBoardDetailResponse;
import com.dansaki.com.temisplacebackend.services.unitAndOrderDetails.UnitOrderDashBoardDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UnitOrderDashBoardDetailController {


    private final UnitOrderDashBoardDetailService unitOrderDashBoardDetailService;

    @PostMapping("unitOrderDashBoardDetails")
    public ResponseEntity<UnitOrderDashBoardDetailResponse> fetchTodayUnitOrderDetails(@RequestParam String unitName){
        return new ResponseEntity<>(unitOrderDashBoardDetailService.fetchTodayUnitOrderDetails(unitName), HttpStatus.OK);
    }
}
