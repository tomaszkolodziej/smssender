package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.Provider;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Tomasz Kołodziej
 */
@Data
@ConfigurationProperties(prefix = "tolean.smssender")
public class GlobalConfiguration {

    private Provider defaultProvider;

}
