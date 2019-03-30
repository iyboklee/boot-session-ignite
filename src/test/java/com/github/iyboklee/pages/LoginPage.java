package com.github.iyboklee.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public Form form() {
        return new Form(getDriver());
    }

    public class Form {

        @FindBy(name = "username")
        private WebElement username;

        @FindBy(name = "password")
        private WebElement password;

        @FindBy(name = "submit")
        private WebElement button;

        public Form(SearchContext context) {
            PageFactory.initElements(new DefaultElementLocatorFactory(context), this);
        }

        public <T> T login(Class<T> page) {
            this.username.sendKeys("user");
            this.password.sendKeys("1234");
            this.button.click();
            return PageFactory.initElements(getDriver(), page);
        }
    }

}
