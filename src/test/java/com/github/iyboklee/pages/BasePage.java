package com.github.iyboklee.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public static void get(WebDriver driver, String url) {
        driver.get(url);
    }

}
