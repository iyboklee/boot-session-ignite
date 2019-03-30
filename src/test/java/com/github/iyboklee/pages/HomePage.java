package com.github.iyboklee.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage go(WebDriver driver, String baseUrl) {
        get(driver, baseUrl + "/");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public boolean containCookie(String name) {
        Set<Cookie> cookies = getDriver().manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name))
                return true;
        }
        return false;
    }

    public LoginPage logout() {
        WebElement logout = getDriver().findElement(By.cssSelector("input[type=\"submit\"]"));
        logout.click();
        return PageFactory.initElements(getDriver(), LoginPage.class);
    }

}
