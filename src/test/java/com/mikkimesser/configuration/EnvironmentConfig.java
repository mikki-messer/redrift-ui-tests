package com.mikkimesser.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration/${environment}.properties")
public interface EnvironmentConfig extends Config {
    String login();

    String password();

    String remoteURL();

    String browserSize();

    String browser();

    String browserVersion();
}
