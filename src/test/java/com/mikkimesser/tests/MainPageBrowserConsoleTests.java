package com.mikkimesser.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainPageBrowserConsoleTests extends TestBase{
    String errorName = testDataConfig.errorName();

    @Test
    @Feature("Main page")
    @Story("Checking console")
    @DisplayName("Check that there are no errors in the console logs")
    public void browserConsoleErrorsTest() {
        redRiftMainPage.openPage();
        redRiftMainPage.checkBrowserConsoleLogs(errorName);
    }
}
