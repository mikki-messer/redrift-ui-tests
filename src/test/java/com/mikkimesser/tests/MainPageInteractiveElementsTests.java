package com.mikkimesser.tests;

import com.mikkimesser.configuration.TestDataConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("mikki_messer")
public class MainPageInteractiveElementsTests extends TestBase {

    String firstURLOfTheFirstContact = testDataConfig.firstURLOfTheFirstContact();
    String secondURLOfTheFirstContact = testDataConfig.secondURLOfTheFirstContact();
    String firstContactName = testDataConfig.nameOfTheFirstContact();


    @Test
    @Feature("Animated lion widget")
    @Story("Browsing main page")
    @DisplayName("Check if the lion widget appears on scrolling to the Success rate block")
    public void showLionTest() {
        redRiftMainPage.openPage();
        redRiftMainPage.checkLionWidgetIsNotVisible();
        redRiftMainPage.scrollToSuccessRateBlock();
        redRiftMainPage.checkLionWidgetIsVisible();
    }

    @Test
    @Feature("Animated lion widget")
    @Story("Browsing main page")
    @DisplayName("Check closing of the lion widget")
    public void closeLionTest() {
        redRiftMainPage.openPage();
        redRiftMainPage.scrollToSuccessRateBlock();
        redRiftMainPage.checkLionWidgetIsVisible();
        redRiftMainPage.closeLionWidget();
        redRiftMainPage.checkLionWidgetIsNotVisible();
    }



    @Test
    @Feature("Popup contact cards")
    @Story("Watching CEO contacts")
    @DisplayName("Check display of the contact card for the first contact")
    public void showCEOContactsTest() {
        redRiftMainPage.openPage();
        redRiftMainPage.scrollToContactsBlock();
        redRiftMainPage.checkContactInfoCardIsNotVisible();
        redRiftMainPage.checkFirstContactName(firstContactName);
        redRiftMainPage.hoverOnContactCard();
        redRiftMainPage.checkContactInfoCardIsVisible();
        redRiftMainPage.checkContactsFirstLink(firstURLOfTheFirstContact);
        redRiftMainPage.checkContactsSecondLink(secondURLOfTheFirstContact);
    }
}

