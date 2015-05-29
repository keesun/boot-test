package me.whiteship.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Keeun Baik
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;

    private String message;
}
