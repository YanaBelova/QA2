package ru.aviasales.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class SearchPage extends Form {
    private ITextBox cityOfDeparture = getElementFactory().getTextBox(By.id("origin"), "City of departure");
    private IComboBox cityAutocomplete = getElementFactory().getComboBox(By.xpath("(//div[@class='autocomplete__dropdown']//*)[1]"), "City autocomplete");
    private ITextBox arrivalCity = getElementFactory().getTextBox(By.id("destination"), "Arrival City");
    private IButton flipThroughTheCalendar = getElementFactory().getButton(By.xpath("//button[contains(@class,'next')]"), "Flip through the calendar");
    private IButton ticketSearch = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Ticket search");
    private ICheckBox typeOfSeatEconomy = getElementFactory().getCheckBox(By.className("custom-radio__element"), "Type of seat Economy");
    private IComboBox seatTypes = getElementFactory().getComboBox(By.xpath("//div[@data-test-id='passengers-field']"), "Seat types");
    private final String attributeValue = "value";

    public SearchPage() {
        super(By.className("header__title"), "Search page");
    }

    public String getCityOfDeparture(){
        return cityOfDeparture.getAttribute(attributeValue);
    }

    public String getArrivalCity(){
        return arrivalCity.getAttribute(attributeValue);
    }

    public void enterCityOfDeparture(String city){
        cityOfDeparture.click();
        cityOfDeparture.sendKeys(city);
    }

    public void selectCityFromAutocomplete(){
        cityAutocomplete.clickAndWait();
    }

    public void enterArrivalCity(String city){
        arrivalCity.click();
        arrivalCity.sendKeys(city);
    }

    public void chooseTheTypeOfSeatEconomy(){
        seatTypes.click();
        typeOfSeatEconomy.check();
    }

    public void selectDate(String date){
        while (AqualityServices.getBrowser().getDriver().findElements(By.xpath(String.format("%s%s%s","//div[contains(@aria-label,'", date,"')]"))).size()==0){
            flipThroughTheCalendar.click();
        }
        try {
            AqualityServices.getBrowser().getDriver().findElement(By.xpath(String.format("%s%s%s","//div[contains(@aria-label,'", date,"')]"))).click();
        }
          catch (NoSuchElementException e){
              AqualityServices.getLogger().error("The date cannot be specified");
          }
    }

    public void clickTicketSearchButton(){
        ticketSearch.clickAndWait();
    }
}
