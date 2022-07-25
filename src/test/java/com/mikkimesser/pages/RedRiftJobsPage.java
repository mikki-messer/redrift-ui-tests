package com.mikkimesser.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RedRiftJobsPage {
    //path
    String pageUrl = "/jobs";

    //locators
    SelenideElement jobsPageHeader = $(".page-inner__title");

    //actions
    @Step("Opening the page")
    public void openPage() {
        open(pageUrl);
    }

    @Step("Checking that jobs page header is {jobsPageHeaderText}")
    public void checkJobsPageHeader(String jobsPageHeaderText) {
        jobsPageHeader.shouldHave(Condition.text(jobsPageHeaderText));
    }


}
