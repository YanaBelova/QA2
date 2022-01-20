package ru.aviasales.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import ru.aviasales.utils.RandomUtils;
import ru.aviasales.utils.configuration.ConfigValuesConfiguration;
import java.util.*;

public class SearchResultsPage extends Form {
    private IComboBox sorting = getElementFactory().getComboBox(By.xpath("//input[@value='trip_duration']/ancestor::div[@class='filter-group']"), "Sorting");
    private ICheckBox sortByTripDuration = getElementFactory().getCheckBox(By.xpath("//input[@value='trip_duration']/../span"), "Sort by trip duration");
    private ITextBox cityOfDeparture = getElementFactory().getTextBox(By.id("origin"), "city of departure");
    private ITextBox arrivalCity = getElementFactory().getTextBox(By.id("destination"), "Arrival City");
    private IComboBox cityAutocomplete = getElementFactory().getComboBox(By.xpath("(//div[@class='autocomplete__dropdown']//*)[1]"), "City autocomplete");
    private IButton ticketSearch = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Ticket search");
    private ITextBox departureDate = getElementFactory().getTextBox(By.xpath("//div[@data-test-id='departure-date-field']"), "Departure date");
    private ITextBox arrivalDate = getElementFactory().getTextBox(By.xpath("//div[@data-test-id='return-date-field']"), "Arrival date");
    private IButton swapPlacesButton = getElementFactory().getButton(By.className("swap-places"), "Swap places");
    private final int lengthOfRandomString =1;
    private final int theNumberOfAttemptsToAchieveASuccessfulResult = 5;
    private final String attributeValue = "value";
    private final String attributeAriaSelected = "aria-selected";
    private final int openedTabNumber = 1;
    private final int partToGetTheIndexToTrimTheString = 1;

    public SearchResultsPage() {
        super(By.xpath("//span[contains(@class,'logo')]"), "Search results page");
    }

    public void openSort(){
        sorting.click();
    }

    public String getCityOfDeparture(){
        return cityOfDeparture.getAttribute(attributeValue);
    }

    public String getArrivalCity(){
        return arrivalCity.getAttribute(attributeValue);
    }

    public void checkSortByTripDuration(){
        sortByTripDuration.check();
    }

    public void switchToActiveTab(){
        switchToAnotherTab(openedTabNumber);
    }

    public void waitingForThePageToLoad(){
        WebDriverWait wait = new WebDriverWait(AqualityServices.getBrowser().getDriver(), Integer.parseInt(ConfigValuesConfiguration.timeOutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("search-countdown__timer")));
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

    public void clickTicketSearchButton(){
        ticketSearch.clickAndWait();
    }

    public void clickSwapPlacesButton(){
        swapPlacesButton.click();
    }

    public void enteringNewDataOnUnsuccessfulSearch(){
        int counter = 0;
        while((AqualityServices.getBrowser().getDriver().findElements(By.className("error-informer__container")).size() >0) && counter < theNumberOfAttemptsToAchieveASuccessfulResult){
            String firstLetterOfCityOfDeparture = RandomUtils.randomString(lengthOfRandomString);
            enterCityOfDeparture(firstLetterOfCityOfDeparture);
            selectCityFromAutocomplete();
            String firstLetterOfArrivalCity = RandomUtils.checkForIdenticalString(lengthOfRandomString, firstLetterOfCityOfDeparture);
            enterArrivalCity(firstLetterOfArrivalCity);
            selectCityFromAutocomplete();
            clickTicketSearchButton();
            waitingForThePageToLoad();
            counter++;
        }
    }

    public void clickDepartureDate(){
        departureDate.click();
    }

    public void clickArrivalDate(){
        arrivalDate.click();
    }

    public boolean getDate(String date){
        return Boolean.parseBoolean(AqualityServices.getBrowser().getDriver().findElement(By.xpath(
                String.format("%s%s%s", "//div[contains(@aria-label,'", date, "')]"))).getAttribute(attributeAriaSelected));
    }

    public List<String> getAListOfFlightDurations(){
        List<WebElement> listOfTickets =  AqualityServices.getBrowser().getDriver().findElements(By.className("segment-route__duration"));
        List<String> listOfFlightTime = new ArrayList<>();
        for (WebElement ticket : listOfTickets){
            String flightTime =  ticket.getText().substring(ticket.getText().indexOf(":")+partToGetTheIndexToTrimTheString).
                    replace('д', ':').replace('ч', ':').replace('м',' ').replaceAll("\\s+","");
            listOfFlightTime.add(flightTime);
        }
        return listOfFlightTime;
    }

    public List<String> getTimeAfterSort(){
        List<String> listOfFlightTime = new ArrayList<>();
        WebElement shortestFlightTicket = AqualityServices.getBrowser().getDriver().findElement(By.xpath("(//div[@class='segment-route__duration'])[1]"));
        String flightTime =  shortestFlightTicket.getText().substring(shortestFlightTicket.getText().indexOf(":")+partToGetTheIndexToTrimTheString).
                replace('д', ':').replace('ч', ':').replace('м',' ').replaceAll("\\s+","");
        listOfFlightTime.add(flightTime);
        return listOfFlightTime;
    }

}
