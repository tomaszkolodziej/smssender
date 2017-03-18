package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Tomasz Kołodziej
 */
@Configuration
@ComponentScan(basePackages = "com.tolean.smssender")
@EnableAsync(proxyTargetClass = true)
@EnableConfigurationProperties({GlobalConfiguration.class, SmsapiProviderConfiguration.class})
public class TestConfiguration {

}
