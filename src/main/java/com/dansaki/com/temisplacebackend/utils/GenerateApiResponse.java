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
    public static final String NO_USER_FOUND = "Account not found" ;
    public static final String USER_CREATED_SUCCESSFULLY = "Account successfully created";
    public static final String INCORRECT_DETAILS = "The details you entered are incorrect";
    public static final String ITEM_SUCCESSFULLY_REMOVED = "Item has been successfully removed from your unit";
    public static final String ITEM_SUCCESSFULLY_ADDED = "Item has been successfully added to your unit";
    public static final String INVALID_CREDENTIALS = "The credentials You Entered Are Invalid";
    public static final String VERIFICATION_SUCCESSFUL ="Your Account has been successfully verified" ;
    public static final String VERIFY_THAT_ITS_YOU = "Verify Otp code" ;
    public static final Object OTP_SENT_SUCCESSFULLY = "Kindly check your email address for your otp";
    public static final String ITEM_NOT_FOUND = "No Item found with the given item title";
    public static final String ITEM_UPDATED_SUCCESSFULLY ="Item updated successfully" ;
    public static final String DELETED_SUCCESSFULLY = "Successfully deleted";
    public static final String FOOTER_CREATED_SUCCESSFULLY = "Footer added successfully" ;
    public static final String FOOTER_UPDATED_SUCCESSFULLY = "Footer successfully updated ";
    public static final String ITEM_SIZE_SUCCESSFULLY_ADDED = "Item size has been successfully added to your unit";
    public static final String ITEM_SIZE_SUCCESSFULLY_REMOVED ="Item size has been successfully removed from your unit" ;
    public static final String HOME_PAGE_DISPLAY_DEACTIVATED = "Home Page Display Deactivated";
    public static final String HOME_PAGE_DISPLAY_ACTIVATED = "Home Page Display Activated";
    public static String ItEM_CATEGORY_AND_IMAGE_SUCCESSFULLY_UPLOADED = "Item category name and image successfully uploaded";
    public static String ItEM_CATEGORY_IMAGE_SUCCESSFULLY_UPDATED = "Item Category image successfully updated";


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

    public static ApiResponse updateSuccessful(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse userNotFound(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    public static ApiResponse incorrectDetails(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    public static ApiResponse okResponse(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse NoSuchItem(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }

    public static ApiResponse deleteSuccessful(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(false)
                .build();
    }
}
