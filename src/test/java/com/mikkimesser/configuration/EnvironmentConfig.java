package com.mikkimesser.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration/${environment}.properties")
public interface EnvironmentConfig extends Config {
    @DefaultValue("false")
    Boolean isRemote();

    String login();

    String password();

    String remoteURL();
}
