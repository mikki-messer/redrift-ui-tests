package com.mikkimesser.tests;

import com.mikkimesser.configuration.TestDataConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainPageMainMenuTests extends TestBase{

    String newsPageHeaderText = testDataConfig.newsPageHeaderText();
    String jobsPageHeaderText = testDataConfig.jobsPageHeaderText();

    @Test
    @Feature("Main menu")
    @Story("Clicking news links")
    @DisplayName("Check the News link")
    public void newsLinkTest() {
        redRiftMainPage.openPage();
        redRiftMainPage.clickOnNewsLink();
        redRiftNewsPage.checkNewsPageHeader(newsPageHeaderText);
    }

    @Test
    @Feature("Main menu")
    @Story("Clicking jobs link")
    @DisplayName("Check the Join our team link")
    public void jobsLinkTest() {
        redRiftMainPage.openPage();
        redRiftMainPage.clickOnJobsLink();
        redRiftJobsPage.checkJobsPageHeader(jobsPageHeaderText);
    }
}
