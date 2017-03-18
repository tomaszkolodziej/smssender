package com.tolean.smssender.providerConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by Tomasz Kołodziej
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "tolean.smssender.smsapi")
public class SmsapiProviderConfiguration implements ProviderConfiguration {

    private String username;
    private String passwordHash;
    private String sender;
    private Boolean fast;
    private Boolean flash;
    private Boolean test;

    @PostConstruct
    public void init() {
        if (isBlank(sender)) {
            sender = "ECO";
        }
        if (fast == null) {
            fast = false;
        }
        if (flash == null) {
            flash = false;
        }
        if (test == null) {
            test = false;
        }
    }

    @Override
    public Provider getType() {
        return Provider.SMSAPI;
    }

}
