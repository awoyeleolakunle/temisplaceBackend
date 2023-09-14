package com.dansaki.com.temisplacebackend.controllers.dashBoardInfoControllers;


import com.dansaki.com.temisplacebackend.services.dashBoardInfo.dashBoardInfoService.DashBoardInfoService;
import com.dansaki.com.temisplacebackend.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class DashBoardInfoController {

    private final DashBoardInfoService dashBoardInfoService;

    @PostMapping("dashBoardInfo")
    public ResponseEntity<ApiResponse> fetchDashBoardInfo(){
        return new ResponseEntity<>(dashBoardInfoService.fetchDashBoardInfo(), HttpStatus.OK);
    }
}
