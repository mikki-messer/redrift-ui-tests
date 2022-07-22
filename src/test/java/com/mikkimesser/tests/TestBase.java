package com.mikkimesser.tests;

import com.codeborne.selenide.Configuration;
import com.mikkimesser.configuration.EnvironmentConfig;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.mikkimesser.configuration.TestDataConfig;
import com.mikkimesser.helpers.Attach;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    static String environment = System.getProperty("environment");
    static EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class, System.getProperties());

    @BeforeAll
    @Step("Preliminary setup")
    public static void setUp() {

        Configuration.baseUrl = System.getProperty("baseURL", "https://redrift.com");
        Configuration.browserSize = System.getProperty("browserSize", "1280x800");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "103.0");

        if (environmentConfig.isRemote()) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                    environmentConfig.login(),
                    environmentConfig.password(),
                    environmentConfig.remoteURL());

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    @Step("Saving screenshot, video, page source, and console logs (except of FireFox)")
    void addAttachments() {
        String screenshotName;                                // Returns a `String`.
        screenshotName = String.format("Screenshot %s", ZonedDateTime                    // Represent a moment as perceived in the wall-clock time used by the people of a particular region ( a time zone).
                .now(                            // Capture the current moment.
                        ZoneId.of("Europe/Moscow")  // Specify the time zone using proper Continent/Region name. Never use 3-4 character pseudo-zones such as PDT, EST, IST.
                )                                // Returns a `ZonedDateTime` object.
                .format(                         // Generate a `String` object containing text representing the value of our date-time object.
                        DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss")
                ));
        Attach.screenshotAs(screenshotName);
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
