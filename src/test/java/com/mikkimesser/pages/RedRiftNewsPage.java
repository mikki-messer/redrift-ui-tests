package com.mikkimesser.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RedRiftNewsPage {
    //path
    String pageUrl = "/news";

    //locators
    SelenideElement newsPageHeader = $(".page-inner__title");

    //actions
    @Step("Opening the page")
    public void openPage() {
        open(pageUrl);
    }

    @Step("Checking the news page header is {newsPageHeaderText}")
    public void checkNewsPageHeader(String newsPageHeaderText) {
        newsPageHeader.shouldHave(Condition.text(newsPageHeaderText));
    }

}
