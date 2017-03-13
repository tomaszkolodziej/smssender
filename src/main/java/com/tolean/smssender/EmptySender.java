package com.tolean.smssender;

import com.tolean.smssender.response.ResponseStatusDetail;

/**
 * Created by Tomasz Kołodziej
 */
public class EmptySender implements SmsSender {

    @Override
    public ResponseStatusDetail send(String text, String phoneNo) {
        return null;
    }

}
