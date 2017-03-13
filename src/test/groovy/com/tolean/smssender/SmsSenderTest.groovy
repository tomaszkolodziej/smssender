package com.tolean.smssender

import com.tolean.smssender.providerConfiguration.SmsapiProviderConfiguration
import com.tolean.smssender.response.ResponseStatus
import com.tolean.smssender.response.ResponseStatusDetail
import spock.lang.Ignore
import spock.lang.Specification
/**
 * Created by Tomasz Kołodziej
 */
class SmsSenderTest extends Specification {

    SmsapiSender smsapiSender = new SmsapiSender(new SmsapiProviderConfiguration("wsiz", "PROVIDE MD5 HASH PASSWORD"))

    @Ignore
    def "should send sms"() {
        when:
            ResponseStatusDetail responseStatusDetail = smsapiSender.send("hello :)", "48663682795")
        then:
            responseStatusDetail.status == ResponseStatus.OK
    }

}