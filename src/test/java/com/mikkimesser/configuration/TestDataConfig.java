package com.mikkimesser.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration/testData.properties")
public interface TestDataConfig extends Config {
    String newsPageHeaderText();

    String jobsPageHeaderText();

    String ceoFacebookURL();

    String ceoLinkedInURL();

    String ceoName();

    String errorName();
}
