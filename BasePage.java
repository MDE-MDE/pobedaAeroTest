package ru.stepup.courses.pobeda.aero.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    @FindBy(css = "header img:first-child")
    private WebElement logoPage;

    @FindBy(css = "a[href='/information']")
    private WebElement info;

    @FindBy(className = "dp-1fh6m6b-root-card")
    protected WebElement findTicket;

    @FindBy(xpath = "//button[contains(text(),'Поиск')]")
    protected WebElement searchBtn;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitlePage(){
        return this.driver.getTitle();
    }

    public boolean logoPageExists(){
        return logoPage.isDisplayed();
    }

    public void scrollToFindTicket() {
        if(!findTicket.isDisplayed()) throw new RuntimeException("Элемента нет на странице");
        Actions actions = new Actions(driver);
        actions.scrollToElement(findTicket).perform();
    }

    public void searchBtnClick(){
        searchBtn.click();
    }
}
