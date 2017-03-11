package com.tolean.smssender.response;

import lombok.AllArgsConstructor;

/**
 * Created by Tomasz Kołodziej
 */
@AllArgsConstructor
public class ResponseStatusDetail {

    private final ResponseStatus status;
    private final String message;

}
