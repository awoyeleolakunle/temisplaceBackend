package com.dansaki.com.temisplacebackend.controllers.userController;


import com.dansaki.com.temisplacebackend.data.models.User;
import com.dansaki.com.temisplacebackend.dtos.request.PaginationRequest;
import com.dansaki.com.temisplacebackend.services.user.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/temisplace/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PaginatedUsersController {
    private final UserService userService;

    @PostMapping("paginatedUserList")
    public ResponseEntity<List<User>> fetchPaginatedUserList(PaginationRequest paginationRequest){
        return new ResponseEntity<>(userService.findPaginatedUserList(paginationRequest), HttpStatus.OK);
    }
}
