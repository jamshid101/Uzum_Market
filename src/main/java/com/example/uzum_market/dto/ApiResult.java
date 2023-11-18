package com.example.uzum_market.dto;

import com.example.uzum_market.exceptions.ErrorData;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    private boolean success;//false

    private String message;//success : true

    private T data;

    private List<com.example.uzum_market.exceptions.ErrorData> errors;

    private ApiResult(T data) {
        this.success = true;
        this.data = data;
    }

    private ApiResult(String message) {
        this.success = true;
        this.message = message;
    }

    private ApiResult(List<com.example.uzum_market.exceptions.ErrorData> errors) {
        this.errors = errors;
    }


    public static ApiResult<?> successResponse(String message) {
        return new ApiResult<>(message);
    }

    public static <E> ApiResult<E> successResponse(E e) {
        return new ApiResult<>(e);
    }

    public static ApiResult<com.example.uzum_market.exceptions.ErrorData> errorResponse(List<com.example.uzum_market.exceptions.ErrorData> errors) {
        return new ApiResult<>(errors);
    }

    public static ApiResult<com.example.uzum_market.exceptions.ErrorData> errorResponse(int status, String msg) {
        return new ApiResult<>(List.of(new ErrorData(status, msg)));
    }
}
