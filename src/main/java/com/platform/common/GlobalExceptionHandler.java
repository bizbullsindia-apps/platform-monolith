package com.platform.common;
import com.platform.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handle(Exception e){ return ApiResponse.fail(e.getMessage()); }
}