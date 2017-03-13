package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import com.tolean.smssender.response.ResponseStatusDetail;
import lombok.extern.slf4j.Slf4j;
import pl.smsapi.Client;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

/**
 * Created by Tomasz Kołodziej
 */
@Slf4j
public class SmsapiSender implements SmsSender {

    private static final String ECO = "ECO";

    private final SmsapiProviderConfiguration configuration;

    private SmsFactory smsFactory;

    public SmsapiSender(SmsapiProviderConfiguration configuration) throws ClientException {
        this.configuration = configuration;
        init();
    }

    private void init() throws ClientException {
        Client client = new Client(configuration.getUsername());
        client.setPasswordHash(configuration.getPasswordHash());
        smsFactory = new SmsFactory(client);
    }

    @Override
    public ResponseStatusDetail send(String text, String phoneNo) {
        SMSSend sms = smsFactory.actionSend()
                .setText(text)
                .setTo(phoneNo)
                .setSender(ECO);
        try {
            sms.execute();
            return ResponseStatusDetail.ok();
        } catch (SmsapiException e) {
            log.error(e.getMessage());
            return ResponseStatusDetail.fail(e);
        }
    }

}
