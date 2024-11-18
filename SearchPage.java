package ru.stepup.courses.pobeda.aero.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[placeholder=\"Откуда\"]")
    private WebElement from;

    @FindBy(css = "input[placeholder=\"Куда\"]")
    private WebElement to;

    @FindBy(css = "input[placeholder=\"Туда\"]")
    private WebElement there;

    @FindBy(css = "input[placeholder=\"Обратно\"]")
    private WebElement back;

    @FindBy(xpath = "//button[contains(text(),'Поиск')]")
    private WebElement searchBtn;

    public boolean findTicketIsDisplayed(){
        return from.isDisplayed() && to.isDisplayed() && there.isDisplayed() && back.isDisplayed();
    }

    public void fillFieldFrom(String fromTheCity){
        from.click();
        from.clear();
        from.sendKeys(fromTheCity);
        from.sendKeys(Keys.ENTER);
    }

    public void fillFieldTo(String inTheCity){
        to.click();
        to.clear();
        to.sendKeys(inTheCity);
        to.sendKeys(Keys.ENTER);
    }

    public boolean fieldThereIsRequired(){
        WebElement parentFieldThere = there.findElement(By.xpath(".."));
        return "true".contains(parentFieldThere.getAttribute("data-errored"));
    }
}
