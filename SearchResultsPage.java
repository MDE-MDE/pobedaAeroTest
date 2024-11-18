package ru.stepup.courses.pobeda.aero.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".dp-1u2q77a-root-group button:last-child")
    private WebElement bookingManagement;

    @FindBy(css = "input[placeholder=\"Фамилия клиента\"]")
    private WebElement lastNameClient;

    @FindBy(css = "input[placeholder=\"Номер бронирования или билета\"]")
    private WebElement bookingTicketNumber;

    @FindBy(className = "message_error")
    private WebElement messageError;

    public void bookingManagementClickTab(){
        bookingManagement.click();
    }

    public boolean bookingManagementIsDisplayed(){
        return lastNameClient.isDisplayed() && bookingTicketNumber.isDisplayed() && searchBtn.isDisplayed();
    }

    public void fillLastNameClient(String lastName){
        lastNameClient.click();
        lastNameClient.sendKeys(lastName);
    }

    public void fillBookingTicketNumber(String bookingOrTicketNumber){
        bookingTicketNumber.click();
        bookingTicketNumber.sendKeys(bookingOrTicketNumber);
    }

    public String getMessageError(){
        List<String> tabs = new ArrayList<>(this.driver.getWindowHandles());
        if(tabs.size() == 1)
            throw new RuntimeException("Вкладка с просмотром заказа не открылась");
        this.driver.switchTo().window(tabs.get(1));
        return messageError.getText();
    }
}
