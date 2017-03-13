package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.ProviderConfiguration;
import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.smsapi.exception.ClientException;

import java.util.Optional;

/**
 * Created by Tomasz Kołodziej
 */
@Configuration
@ConditionalOnClass({EnableSmsSender.class})
@EnableConfigurationProperties({GlobalConfiguration.class, SmsapiProviderConfiguration.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsSenderAutoconfigure {

    private final GlobalConfiguration globalConfiguration;
    private final SmsapiProviderConfiguration smsapiProviderConfiguration;

    @Bean
    @ConditionalOnClass({EnableSmsSender.class})
    public SmsSender smsSender() throws ClientException {
        Optional<ProviderConfiguration> providerConfigurationOptional = getProviderConfiguration();

        if (providerConfigurationOptional.isPresent()) {
            return new SmsapiSender((SmsapiProviderConfiguration) providerConfigurationOptional.get());
        } else {
            return new EmptySender();
        }
    }

    private Optional<ProviderConfiguration> getProviderConfiguration() {
        switch (globalConfiguration.getDefaultProvider()) {
            case SMSAPI: {
                return Optional.of(smsapiProviderConfiguration);
            }
        }
        return Optional.empty();
    }

}
