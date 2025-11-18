package com.cariad.hackathon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {

    private String message;
    private T data;
    private int status;
    private ErrorDetails error;

    public static <T> APIResponse<T> success(T data) {
        return APIResponse.<T>builder()
                .status(200)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> success(String message, T data) {
        return APIResponse.<T>builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> success(String message, int status, T data) {
        return APIResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> APIResponse<T> error(String message) {
        return APIResponse.<T>builder()
                .status(400)
                .message(message)
                .build();
    }

    public static <T> APIResponse<T> error(String message, ErrorDetails error) {
        return APIResponse.<T>builder()
                .status(400)
                .message(message)
                .error(error)
                .build();
    }

    public static <T> APIResponse<T> error(String message, int status, ErrorDetails error) {
        return APIResponse.<T>builder()
                .status(status)
                .message(message)
                .error(error)
                .build();
    }

}
