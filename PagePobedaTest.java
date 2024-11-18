package ru.stepup.courses.pobeda.aero.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class PagePobedaTest {
    private WebDriver driver;
    private BasePage basePage;
    private PopupPage popupPage;
    private SearchPage searchPage;
    private SearchResultsPage searchResultsPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Comp\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        System.setProperty("webdriver.edge.whitelistedIps", "");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://pobeda.aero/");

        basePage = new BasePage(driver);
        popupPage = new PopupPage(driver);
        searchPage = new SearchPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }

    private void verifyLogoTitlePage(){
        Assert.assertTrue(basePage.getTitlePage().contains("Авиакомпания «Победа» - купить"));
        Assert.assertTrue(basePage.logoPageExists());
    }

    @Test
    public void popupInfoPobedaPage(){
        verifyLogoTitlePage();

        popupPage.hoverInfo();

        Assert.assertTrue(popupPage.getInfoFlight().contains("Подготовка к полёту"));
        Assert.assertTrue(popupPage.getInfoUseful().contains("Полезная информация"));
        Assert.assertTrue(popupPage.getInfoCompany().contains("О компании"));
    }

    @Test
    public void initiatingSearchPobedaPage(){
        verifyLogoTitlePage();

        searchPage.scrollToFindTicket();

        Assert.assertTrue(searchPage.findTicketIsDisplayed());

        searchPage.fillFieldFrom("Москва");
        searchPage.fillFieldTo("Санкт-Петербург");
        searchPage.searchBtnClick();

        Assert.assertTrue(searchPage.fieldThereIsRequired());
    }

    @Test
    public void searchResultPobedaPage(){
        verifyLogoTitlePage();

        searchResultsPage.scrollToFindTicket();
        searchResultsPage.bookingManagementClickTab();

        Assert.assertTrue(searchResultsPage.bookingManagementIsDisplayed());

        searchResultsPage.fillLastNameClient("Qwerty");
        searchResultsPage.fillBookingTicketNumber("XXXXXX");
        searchResultsPage.searchBtnClick();

        Assert.assertTrue(searchResultsPage.getMessageError().contains("Заказ с указанными параметрами не найден"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
