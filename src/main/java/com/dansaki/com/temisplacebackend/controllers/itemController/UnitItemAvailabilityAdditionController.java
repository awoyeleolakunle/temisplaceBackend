package com.dansaki.com.temisplacebackend.controllers.itemController;


import com.dansaki.com.temisplacebackend.dtos.request.UnitItemAvailabilityRequest;
import com.dansaki.com.temisplacebackend.services.item.unitAndItem.unitItemAddition.UnitItemAvailabilityAdditionService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UnitItemAvailabilityAdditionController {
    private final UnitItemAvailabilityAdditionService unitItemAvailabilityAdditionService;

    @PostMapping("unitItemAvailabilityAddition")
    public ResponseEntity<ApiResponse> addItemAvailabilityToUnit(@RequestBody UnitItemAvailabilityRequest unitItemAvailabilityRequest){
    return new ResponseEntity<>(unitItemAvailabilityAdditionService.addItemAvailabilityToUnit(unitItemAvailabilityRequest), HttpStatus.OK);
    }
}
