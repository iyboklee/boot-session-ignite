package com.github.iyboklee;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;

import com.github.iyboklee.pages.HomePage;
import com.github.iyboklee.pages.LoginPage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrateTests {

    @Value("${server.port}") private int port;

    @Autowired private MockMvc mockMvc;

    private String baseUrl;
    private WebDriver driver;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port;
        driver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(this.mockMvc).build();
    }

    @After
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void STEP01_로그인_후_홈페이지에_진입한다() {
        LoginPage loginPage = HomePage.go(driver, baseUrl);
        assertThat("로그인 페이지 제목은 `Login Page`이다", loginPage.getTitle(), is("Login Page"));
        HomePage homePage = loginPage.form().login(HomePage.class);
        assertThat("홈페이지 제목은 `Secured Content`이다", homePage.getTitle(), is("Secured Content"));
    }

    @Test
    public void STEP02_로그인_후_SESSION_쿠키값을_가지고있다() {
        LoginPage loginPage = HomePage.go(driver, baseUrl);
        HomePage homePage = loginPage.form().login(HomePage.class);

        assertTrue("SESSION 쿠키를 가지고 있다.", homePage.containCookie("SESSION"));
        assertFalse("JSESSIONID 쿠키  를 가지고 있지 않다.", homePage.containCookie("JSESSIONID"));
    }

    @Test
    public void STEP03_로그아웃_후_로그인_페이지에_진입한다() {
        LoginPage loginPage = HomePage.go(driver, baseUrl);
        HomePage homePage = loginPage.form().login(HomePage.class);
        loginPage = homePage.logout();
        assertThat("로그아웃 후 페이지 제목은 `Login Page`이다", loginPage.getTitle(), is("Login Page"));
    }

}
