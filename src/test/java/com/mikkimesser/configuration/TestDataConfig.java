package com.mikkimesser.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration/testData.properties")
public interface TestDataConfig extends Config {
    String newsPageHeaderText();

    String jobsPageHeaderText();

    String firstURLOfTheFirstContact();

    String secondURLOfTheFirstContact();

    String nameOfTheFirstContact();

    String errorName();
}
