package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import com.tolean.smssender.response.ResponseStatusDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.smsapi.Client;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

import javax.annotation.PostConstruct;

/**
 * Created by Tomasz Kołodziej
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsapiSender implements SmsSender {

    private final SmsapiProviderConfiguration configuration;
    private SmsFactory smsFactory;

    @PostConstruct
    public void init() throws ClientException {
        Client client = new Client(configuration.getUsername());
        client.setPasswordHash(configuration.getPasswordHash());
        smsFactory = new SmsFactory(client);
    }

    @Async(ThreadPoolTaskExecutor.NAME)
    public ResponseStatusDetail send(String text, String phoneNo) {
        SMSSend sms = smsFactory.actionSend()
                .setText(text)
                .setTo(phoneNo)
                .setSender(configuration.getSender())
                .setFast(configuration.getFast())
                .setFlash(configuration.getFlash())
                .setTest(configuration.getTest());
        try {
            sms.execute();
            return ResponseStatusDetail.ok();
        } catch (SmsapiException e) {
            log.error(e.getMessage());
            return ResponseStatusDetail.fail(e);
        }
    }

}
