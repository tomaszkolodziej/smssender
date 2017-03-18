package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.ProviderConfiguration;
import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
@ConditionalOnBean(annotation = EnableSmsSender.class)
@EnableAsync
@EnableConfigurationProperties({GlobalConfiguration.class, SmsapiProviderConfiguration.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsSenderAutoConfigure {

    private final GlobalConfiguration globalConfiguration;
    private final SmsapiProviderConfiguration smsapiProviderConfiguration;

    @Bean
    @ConditionalOnMissingBean
    public SmsSender smsSender() throws ClientException {
        Optional<ProviderConfiguration> providerConfigurationOptional = getProviderConfiguration();

        if (!providerConfigurationOptional.isPresent()) {
            return new EmptySender();
        }

        switch (providerConfigurationOptional.get().getType()) {
            case SMSAPI: return new SmsapiSender((SmsapiProviderConfiguration) providerConfigurationOptional.get());
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
