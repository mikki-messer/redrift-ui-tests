package com.mikkimesser.tests;

import com.mikkimesser.pages.RedRiftMainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("mikki_messer")
public class MainPageUITests extends TestBase {

    /*
    TODO
    1. Добавить переключатель, систем проперти remote/local +
    2. Вынести тестовые данные в файл пропертис +
    3. Добавить сторис и оунера +
    4. Новый репо +
    5. Джоб в дженкинс +
    6. Добавить интеграцию с аллюр тестопс +
    7. Настроить в джобе отправку запусков в аллюр +
    8. Переснять скриншоты для ридми
    9. Прогнать текст ридми через граммарли
     */

    @Test
    @Feature("Animated lion widget")
    @Story("Browsing main page")
    @DisplayName("Check if the lion widget appears on scrolling to the Success rate block")
    public void showLionTest() {
        RedRiftMainPage redRiftMainPage = new RedRiftMainPage();
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
        RedRiftMainPage redRiftMainPage = new RedRiftMainPage();
        redRiftMainPage.openPage();
        redRiftMainPage.scrollToSuccessRateBlock();
        redRiftMainPage.checkLionWidgetIsVisible();
        redRiftMainPage.closeLionWidget();
        redRiftMainPage.checkLionWidgetIsNotVisible();
    }

    @Test
    @Feature("Main menu")
    @Story("Clicking news links")
    @DisplayName("Check the News link")
    public void newsLinkTest() {
        RedRiftMainPage redRiftMainPage = new RedRiftMainPage();
        redRiftMainPage.openPage();
        redRiftMainPage.clickOnNewsLink();
        redRiftMainPage.checkNewsPageHeader();
    }

    @Test
    @Feature("Main menu")
    @Story("Clicking jobs link")
    @DisplayName("Check the Join our team link")
    public void jobsLinkTest() {
        RedRiftMainPage redRiftMainPage = new RedRiftMainPage();
        redRiftMainPage.openPage();
        redRiftMainPage.clickOnJobsLink();
        redRiftMainPage.checkJobsPageHeader();
    }

    @Test
    @Feature("Popup contact cards")
    @Story("Watching CEO contacts")
    @DisplayName("Check display of the contact card for the first contact")
    public void showCEOContactsTest() {
        RedRiftMainPage redRiftMainPage = new RedRiftMainPage();
        redRiftMainPage.openPage();
        redRiftMainPage.scrollToContactsBlock();
        redRiftMainPage.checkContactInfoCardIsNotVisible();
        redRiftMainPage.checkFirstContactName();
        redRiftMainPage.hoverOnContactCard();
        redRiftMainPage.checkContactInfoCardIsVisible();
        redRiftMainPage.checkContactsFirstLink();
        redRiftMainPage.checkContactsSecondLink();
    }

    @Test
    @Feature("Main page")
    @Story("Checking console")
    @DisplayName("Check that there are no SEVERE errors in the console logs")
    public void browserConsoleErrorsTest() {
        RedRiftMainPage redRiftMainPage = new RedRiftMainPage();
        redRiftMainPage.openPage();
        redRiftMainPage.checkBrowserConsoleLogs();
    }
}

