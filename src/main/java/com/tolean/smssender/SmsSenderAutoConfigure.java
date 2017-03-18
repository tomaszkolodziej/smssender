package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.ProviderConfiguration;
import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.smsapi.exception.ClientException;

import java.util.Optional;
import java.util.concurrent.Executor;

/**
 * Created by Tomasz Kołodziej
 */
@Configuration
@ComponentScan
@EnableAsync
@EnableConfigurationProperties({GlobalConfiguration.class, SmsapiProviderConfiguration.class})
public class SmsSenderAutoConfigure {

    private final GlobalConfiguration globalConfiguration;
    private final SmsapiProviderConfiguration smsapiProviderConfiguration;

    @Autowired
    public SmsSenderAutoConfigure(GlobalConfiguration globalConfiguration,
                                  SmsapiProviderConfiguration smsapiProviderConfiguration) {
        this.globalConfiguration = globalConfiguration;
        this.smsapiProviderConfiguration = smsapiProviderConfiguration;
    }

    @Bean
    public SmsSender smsSender() throws ClientException {
        Optional<ProviderConfiguration> providerConfigurationOptional = getProviderConfiguration();

        if (!providerConfigurationOptional.isPresent()) {
            return new EmptySender();
        }

        switch (providerConfigurationOptional.get().getType()) {
            case SMSAPI: return new SmsapiSender(smsapiProviderConfiguration);
            default: return new EmptySender();
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

    @Bean(name = "smsSenderThreadPoolTaskExecutor")
    public Executor smsSenderThreadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

}
