package org.lzq.nyy.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int statusCode;
    private boolean success;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int statusCode, boolean success, String message, T data) {
        this.statusCode = statusCode;
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
