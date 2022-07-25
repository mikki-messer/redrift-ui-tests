package com.mikkimesser.tests;

import com.codeborne.selenide.Configuration;
import com.mikkimesser.configuration.EnvironmentConfig;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.mikkimesser.configuration.TestDataConfig;
import com.mikkimesser.helpers.Attach;
import com.mikkimesser.pages.RedRiftJobsPage;
import com.mikkimesser.pages.RedRiftMainPage;
import com.mikkimesser.pages.RedRiftNewsPage;
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
    static RedRiftMainPage redRiftMainPage = new RedRiftMainPage();
    static RedRiftNewsPage redRiftNewsPage = new RedRiftNewsPage();
    static RedRiftJobsPage redRiftJobsPage = new RedRiftJobsPage();
    static TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class);

    @BeforeAll
    @Step("Preliminary setup")
    public static void setUp() {

        Configuration.baseUrl = System.getProperty("baseURL", "https://redrift.com");
        Configuration.browserSize = System.getProperty("browserSize", "1280x800");
        Configuration.browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "");

        if (browserVersion.isEmpty())
            Configuration.browserVersion = browserVersion;

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
        Attach.screenshotWithTimeStamp();
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

}
