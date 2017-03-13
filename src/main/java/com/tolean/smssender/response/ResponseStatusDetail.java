package com.tolean.smssender.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Tomasz Kołodziej
 */
@Getter
@AllArgsConstructor
public class ResponseStatusDetail {

    private final ResponseStatus status;
    private final String message;

    public static ResponseStatusDetail ok() {
        return new ResponseStatusDetail(ResponseStatus.OK, null);
    }

    public static ResponseStatusDetail fail(Exception exception) {
        return new ResponseStatusDetail(ResponseStatus.FAIL, exception.getMessage());
    }

    public static ResponseStatusDetail fail(String message) {
        return new ResponseStatusDetail(ResponseStatus.FAIL, message);
    }

    public boolean isSuccess() {
        return status == ResponseStatus.OK;
    }

    public boolean isFail() {
        return status == ResponseStatus.FAIL;
    }

}
