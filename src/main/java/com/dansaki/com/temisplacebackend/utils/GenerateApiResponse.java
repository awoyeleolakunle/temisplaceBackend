package com.dansaki.com.temisplacebackend.utils;

import org.springframework.http.HttpStatus;

public class GenerateApiResponse {

    public static final String BEARER ="Bearer ";
    public static final String POST_SUCCESSFULLY_CREATED = "Post successfully created";
    public static final String POST_ALREADY_EXIST = "Post with this title already exist";
    public static final String STATUS_UPDATED_SUCCESSFULLY = "Status updated successfully";
    public static final String ORDER_ALREADY_COMPLETED = "Sorry, you can't perform this operation because order is already completed";
    public static final String ORDER_ALREADY_CANCELLED = "Sorry, you can't perform this operation because order is already cancelled";
    public static final String ORDER_NOT_FOUND = "No order with this details";
    public static final String BLOG_POST_NOT_FOUND = "No blog post with this details";
    public static final String USER_ALREADY_EXIST ="Account already exists" ;
    public static final String ITEM_CREATED_SUCCESSFULLY = "Item has been successfully added";


    public static ApiResponse createdResponse(Object data){
        return ApiResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .data(data)
                .build();
    }

    public static ApiResponse alreadyCreated(Object data) {
        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .isSuccessful(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .data(data)
                .build();
    }

    public static ApiResponse UpdateStatus(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse dashBoardInfo(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse alreadyCompleted(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    public static ApiResponse alreadyCancelled(Object  data){
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    public static ApiResponse noSuchOrder(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    public static ApiResponse blogPostNotFound(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    public static ApiResponse updateSucessful(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }
}
