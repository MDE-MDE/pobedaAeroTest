package ru.stepup.courses.pobeda.aero.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PopupPage extends BasePage {

    public PopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href='/information']")
    private WebElement info;

    @FindBy(className = "dp-1io9deb-root")
    private WebElement infoMenu;

    @FindBy(css = "a[href='/information#flight']")
    private WebElement infoFlight;

    @FindBy(css = "a[href='/information#useful']")
    private WebElement infoUseful;

    @FindBy(css = "a[href='/information#company']")
    private WebElement infoCompany;

    public void hoverInfo() {
        Actions actions = new Actions(driver);
        actions.moveToElement(info).perform();
        if (!infoMenu.isDisplayed())
            throw new RuntimeException("Элемент не появился на странице");
    }

    public String getInfoFlight(){
        hoverInfo();
        return infoFlight.getText();
    }

    public String getInfoUseful(){
        hoverInfo();
        return infoUseful.getText();
    }

    public String getInfoCompany(){
        hoverInfo();
        return infoCompany.getText();
    }
}
