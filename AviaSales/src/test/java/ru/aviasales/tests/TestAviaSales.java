package ru.aviasales.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.aviasales.forms.SearchPage;
import ru.aviasales.forms.SearchResultsPage;
import ru.aviasales.utils.RandomUtils;
import ru.aviasales.utils.TimeUtils;
import ru.aviasales.utils.WaitUtils;
import ru.aviasales.utils.configuration.ConfigValuesConfiguration;
import java.util.Collections;

public class TestAviaSales extends BaseTest{
    private SearchPage searchPage = new SearchPage();
    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private final int lengthOfRandomString = 1;
    private final long timeOfImplicitlyWait = 10000;
    private final int fastestFlightIndexAfterSorting = 0;

    @Test
    public void testAviaSales(){
    stepLogger(1, String.format("%s%s", "Перейти на сайт ", ConfigValuesConfiguration.baseURL));

    String firstLetterOfCityOfDeparture = RandomUtils.randomString(lengthOfRandomString);
    searchPage.enterCityOfDeparture(firstLetterOfCityOfDeparture);
    searchPage.selectCityFromAutocomplete();
    stepLogger(2, "Departure city entered");
    String firstLetterOfArrivalCity = RandomUtils.checkForIdenticalString(lengthOfRandomString, firstLetterOfCityOfDeparture);
    searchPage.enterArrivalCity(firstLetterOfArrivalCity);
    searchPage.selectCityFromAutocomplete();
    stepLogger(3, "City of arrival introduced");
    searchPage.selectDate(ConfigValuesConfiguration.departureDate);
    stepLogger(4, "Departure date entered");
    searchPage.selectDate(ConfigValuesConfiguration.arrivalDate );
    stepLogger(5, "Return date entered");
    searchPage.chooseTheTypeOfSeatEconomy();
    stepLogger(6, "Economy seat type selected");
    searchPage.clickTicketSearchButton();
    stepLogger(7, "The Find button was pressed");
    searchResultsPage.switchToActiveTab();
    searchResultsPage.waitingForThePageToLoad();
    stepLogger(8, "New page loaded");

    searchResultsPage.enteringNewDataOnUnsuccessfulSearch();
    WaitUtils.implicitlyWait(timeOfImplicitlyWait);
    searchResultsPage.getAListOfFlightDurations();
    int minimumFlightTimeFromTheList = Collections.min(TimeUtils.convertToMinutes(searchResultsPage.getAListOfFlightDurations()));

    Assert.assertEquals(searchPage.getCityOfDeparture(), searchResultsPage.getCityOfDeparture(), "The city of departure didn't match");
    Assert.assertEquals(searchPage.getArrivalCity(), searchResultsPage.getArrivalCity(), "The arrival city didn't match");
    searchResultsPage.clickDepartureDate();
    Assert.assertTrue(searchResultsPage.getDate(ConfigValuesConfiguration.departureDate), "Departure date does not match");
    searchResultsPage.clickArrivalDate();
    Assert.assertTrue(searchResultsPage.getDate(ConfigValuesConfiguration.arrivalDate), "Arrival date does not match");
    stepLogger(9, "The entered data matches the search data");

    searchResultsPage.openSort();
    searchResultsPage.checkSortByTripDuration();
    WaitUtils.implicitlyWait(timeOfImplicitlyWait);
    int minimumTimeAfterSorting = TimeUtils.convertToMinutes(searchResultsPage.getTimeAfterSort()).get(fastestFlightIndexAfterSorting);

    Assert.assertEquals(minimumFlightTimeFromTheList, minimumTimeAfterSorting, "Fastest search and sort flight does not match");
    stepLogger(10, "The fastest flight from the initial list is the same as the first one sorted by Trip duration");

    searchResultsPage.clickSwapPlacesButton();
    searchResultsPage.clickTicketSearchButton();
    stepLogger(11, "Direction reversed");
    searchResultsPage.enteringNewDataOnUnsuccessfulSearch();
    searchResultsPage.getAListOfFlightDurations();
    int minimumFlightTimeFromTheListInTheOppositeDirection = Collections.min(TimeUtils.convertToMinutes(searchResultsPage.getAListOfFlightDurations()));
    searchResultsPage.openSort();
    searchResultsPage.checkSortByTripDuration();
    WaitUtils.implicitlyWait(timeOfImplicitlyWait);
    int minimumTimeAfterSortingInTheOppositeDirection = TimeUtils.convertToMinutes(searchResultsPage.getTimeAfterSort()).get(fastestFlightIndexAfterSorting);

    Assert.assertEquals(minimumFlightTimeFromTheListInTheOppositeDirection, minimumTimeAfterSortingInTheOppositeDirection, "In the opposite direction, the fastest search and sort flight does not match");
    stepLogger(10, "In the opposite direction, the fastest flight from the initial list is the same as the first one sorted by Trip duration.");
    }
}