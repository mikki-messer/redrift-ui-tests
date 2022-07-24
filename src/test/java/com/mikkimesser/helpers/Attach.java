package com.mikkimesser.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.mikkimesser.configuration.EnvironmentConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {
    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void screenshotWithTimeStamp() {
        String screenshotAlias = generateScreenshotAlias();
        screenshotAs(screenshotAlias);
    }

    public static void browserConsoleLogs() {
        //Mike 2022-06-15
        //FireFox driver doesn't support saving console logs
        if (Objects.equals(Configuration.browser.toLowerCase(Locale.ROOT), "firefox"))
            return;

        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl(getSessionId())
                + "' type='video/mp4'></video></body></html>";
    }

    public static URL getVideoUrl(String sessionId) {
        EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class, System.getProperties());
        String selenoidURL = String.format("https://%s/video/", environmentConfig.remoteURL());
        String videoUrl = String.format("%s%s.mp4", selenoidURL, sessionId);

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

    public static String generateScreenshotAlias() {
        return String.format("Screenshot %s", ZonedDateTime                    // Represent a moment as perceived in the wall-clock time used by the people of a particular region ( a time zone).
                .now(                            // Capture the current moment.
                        ZoneId.of("Europe/Belgrade")  // Specify the time zone using proper Continent/Region name. Never use 3-4 character pseudo-zones such as PDT, EST, IST.
                )                                // Returns a `ZonedDateTime` object.
                .format(                         // Generate a `String` object containing text representing the value of our date-time object.
                        DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss")
                ));
    }
}
