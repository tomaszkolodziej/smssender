package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.ProviderConfiguration;
import com.tolean.smssender.providerConfiguration.ProviderConfigurationFactory;
import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.smsapi.exception.ClientException;

/**
 * Created by Tomasz Kołodziej
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsSenderFactory {

    private final ProviderConfigurationFactory providerConfigurationFactory;

    @Bean
    @ConditionalOnClass(value = {EnableSmsSender.class})
    public SmsSender smsSender() throws ClientException {
        ProviderConfiguration providerConfiguration = providerConfigurationFactory.providerConfiguration();

        switch (providerConfiguration.getType()) {
            case SMSAPI: {
                return new SmsapiSender((SmsapiProviderConfiguration) providerConfiguration);
            }
            default: {
                return new EmptySender();
            }
        }
    }

}
