package com.tolean.smssender;

import com.tolean.smssender.response.ResponseStatusDetail;

/**
 * Created by Tomasz Kołodziej
 */
public interface SmsSender {

    ResponseStatusDetail send(String text, String phoneNo);

}
