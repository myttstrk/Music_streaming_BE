package com.musicstreaming.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success = false;
    private String message;
    private T payload;
    private List<String> errors;
    private String error;
    private Long id;
}
