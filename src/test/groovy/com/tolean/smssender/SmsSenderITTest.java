package com.tolean.smssender;

import com.tolean.smssender.providerConfiguration.Provider;
import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tomasz Kołodziej
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class SmsSenderITTest {

    @Autowired
    private GlobalConfiguration globalConfiguration;

    @Autowired
    private SmsapiProviderConfiguration smsapiProviderConfiguration;

    @Test
    public void shouldLoadGlobalConfiguration() {
        assertEquals(Provider.SMSAPI, globalConfiguration.getDefaultProvider());
    }

    @Test
    public void shouldLoadSmsApiProviderConfiguration() {
        assertEquals("user", smsapiProviderConfiguration.getUsername());
    }

}
