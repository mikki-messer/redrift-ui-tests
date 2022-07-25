package com.mikkimesser.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.mikkimesser.configuration.TestDataConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.logging.LogType.BROWSER;


public class RedRiftMainPage {
    //locators
    SelenideElement successRateBlock = $(byText("Success rate"));
    SelenideElement lionWidget = $(".leon__message");
    SelenideElement lionWidgetCloseButton = $(".btn-close");
    SelenideElement newsMenuLink = $(byLinkText("news"));
    SelenideElement jobsMenuLink = $(byLinkText("join our team!"));
    SelenideElement contactsBlock = $(byText("Who We"));
    SelenideElement contactInfoCard = $(".worker-social-list");
    SelenideElement contactName = $(".worker-card__description-name");
    SelenideElement contactCard = $(".worker-card__addition-info");
    SelenideElement firstLink = contactCard.$(".worker-social-list__link");
    SelenideElement secondLink = firstLink.sibling(0);
    //path
    String pageUrl = "/";

    //actions
    @Step("Opening the page")
    public void openPage() {
        open(pageUrl);
    }

    @Step("Scrolling to the Success Rate block")
    public void scrollToSuccessRateBlock() {
        successRateBlock.scrollTo();
    }

    @Step("Checking if the lion widget is invisible")
    public void checkLionWidgetIsNotVisible() {
        lionWidget.shouldNotBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Checking if the lion widget is visible")
    public void checkLionWidgetIsVisible() {
        lionWidget.shouldBe(Condition.visible);
    }

    @Step("Closing the lion widget")
    public void closeLionWidget() {
        lionWidgetCloseButton.click();
    }

    @Step("Clicking the news link")
    public void clickOnNewsLink() {
        newsMenuLink.click();
    }

    @Step("Clicking on the jobs link")
    public void clickOnJobsLink() {
        jobsMenuLink.click();
    }


    @Step("Scrolling to the contacts block")
    public void scrollToContactsBlock() {
        contactsBlock.scrollTo();
    }

    @Step("Checking if the contact info card is invisible")
    public void checkContactInfoCardIsNotVisible() {
        contactInfoCard.shouldNotBe(Condition.visible);
    }

    @Step("Checking if the contact info card is visible")
    public void checkContactInfoCardIsVisible() {
        contactInfoCard.shouldBe(Condition.visible);
    }

    @Step("Checking that name of the first contact equals to {firstContactName}")
    public void checkFirstContactName(String firstContactName) {
        contactName.shouldHave(Condition.text(firstContactName));
    }

    @Step("Hovering on the first contact card")
    public void hoverOnContactCard() {
        contactCard.hover();
    }

    @Step("Checking that the first link on the contact card is {firstLinkURL}")
    public void checkContactsFirstLink(String firstLinkURL) {
        firstLink.shouldHave(Condition.href(firstLinkURL));
    }

    @Step("Checking that the second link on the contact card is {secondLinkURL}")
    public void checkContactsSecondLink(String secondLinkURL) {
        secondLink.shouldHave(Condition.href(secondLinkURL));
    }

    @Step("Checking that browser console logs do not contain {errorName} errors")
    public void checkBrowserConsoleLogs(String errorName) {
        String consoleLogs = String.join("\n", Selenide.getWebDriverLogs(BROWSER));
        assertThat(consoleLogs).doesNotContain(errorName);
    }
}

