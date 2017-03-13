package com.tolean.smssender.providerConfiguration;

import com.tolean.smssender.GlobalConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tomasz Kołodziej
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProviderConfigurationFactory {

    private final GlobalConfiguration globalConfiguration;
    private final SmsapiProviderConfiguration smsapiProviderConfiguration;

    @Bean
    public ProviderConfiguration providerConfiguration() {
        switch (globalConfiguration.getDefaultProvider()) {
            case SMSAPI: {
                return smsapiProviderConfiguration;
            }
        }
        return null;
    }

}
