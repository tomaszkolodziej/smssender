package com.tolean.smssender.providerConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Tomasz Kołodziej
 */
@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "tolean.smssender.smsapi")
public class SmsapiProviderConfiguration implements ProviderConfiguration {

    private String username;
    private String passwordHash;

    @Override
    public Provider getType() {
        return Provider.SMSAPI;
    }

}
