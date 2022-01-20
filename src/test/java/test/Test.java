package test;
import APIModels.Post;
import APIModels.User;
import aquality.selenium.browser.AqualityServices;
import DatabaseModels.CreatingDBModel;
import utils.*;
import api.task.*;
import kong.unirest.*;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Test{
    private DBUtils databaseUtils = new DBUtils(JsonUtils.getValueFromDatabasePasswordJson("/url"),JsonUtils.getValueFromDatabasePasswordJson("/username"),
            JsonUtils.getValueFromDatabasePasswordJson("/password"));
    private RandomStringGenerator randomStringGenerator =
        new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS)
                .build();
    private final int lengthOfTitleString = 12;
    private final int lengthOfBodyString = 10;
    private final int timeOfSleep =1000;
    private API<Object> api = new API<>();
    private final String randomTitleString = randomStringGenerator.generate(lengthOfTitleString);
    private final String randomBodyString = randomStringGenerator.generate(lengthOfBodyString);
    private String[] valuesArray  = {randomTitleString, randomBodyString, JsonUtils.getValueFromTestDataJson("/userIdToPostAPI")};
    private String[] keysArray = {JsonUtils.getValueFromConfigJson("/titleRowName"), JsonUtils.getValueFromConfigJson("/bodyRowName"), JsonUtils.getValueFromConfigJson("/userIdRowName")};
    private Map<String, Object> values = MapUtils.getMap(keysArray, valuesArray);
    private Config expected = API.baseURL(JsonUtils.getValueFromConfigJson("/baseURL"));
    private HttpResponse<List<Post>> responseOfPorts = APIUtils.getAPIPostList(JsonUtils.getValueFromConfigJson("/postsURL"));
    private HttpResponse<Post> responseOf99Port = (HttpResponse<Post>) api.getAPIObject(String.format("%s%s",
            JsonUtils.getValueFromConfigJson("/postsURL"),
            JsonUtils.getValueFromConfigJson("/99postURL")), new Post());
    private HttpResponse<Post> responseOf150Port = (HttpResponse<Post>) api.getAPIObject(String.format("%s%s",
            JsonUtils.getValueFromConfigJson("/postsURL"),
            JsonUtils.getValueFromConfigJson("/150postURL")), new Post());
    private HttpResponse<Post> responseOfPostAPI = (HttpResponse<Post>) api.postAPIPostObj(new Post(),
            JsonUtils.getValueFromConfigJson("/postsURL"), values);
    private HttpResponse<String> responseOfUsers = APIUtils.getAPIAsString(JsonUtils.getValueFromConfigJson("/usersURL"));
    private HttpResponse<User> responseOfUser5 = (HttpResponse<User>) api.getAPIObject(String.format("%s%s",
            JsonUtils.getValueFromConfigJson("/usersURL"),
            JsonUtils.getValueFromConfigJson("/5usersURL")), new User());

    @org.testng.annotations.Test
     public void test() throws UnirestException {
        Assert.assertEquals(HttpStatus.OK, API.getStatusCode(responseOfPorts), "Status code is not 200");
        Assert.assertTrue(APIUtils.isJSONValid(responseOfPorts.getBody().toString()), "List format is not json");
        Assert.assertTrue(APIUtils.checkSort(responseOfPorts), "Values by id are not sorted");

        Assert.assertEquals(HttpStatus.OK, API.getStatusCode(responseOf99Port), "Status code is not 200");
        Assert.assertTrue(APIUtils.notNullValue(APIUtils.getBodyOfPostObj(responseOf99Port).getTitle()), "The string is empty");
        Assert.assertTrue(APIUtils.notNullValue(APIUtils.getBodyOfPostObj(responseOf99Port).getBody()), "The string is empty");
        Assert.assertEquals(Integer.parseInt(JsonUtils.getValueFromTestDataJson("/userIdOf99port")), APIUtils.getBodyOfPostObj(responseOf99Port).getUserId(), "UserId is not 10");
        Assert.assertEquals(Integer.parseInt(JsonUtils.getValueFromTestDataJson("/idOf99port")), APIUtils.getBodyOfPostObj(responseOf99Port).getId(), "Id is not 99");

        Assert.assertEquals(HttpStatus.NOT_FOUND, API.getStatusCode(responseOf150Port), "Status code is not 404");
        Assert.assertTrue(APIUtils.notNullObject(responseOf150Port), "JsonObject isn't null");

        Assert.assertEquals(HttpStatus.CREATED, API.getStatusCode(responseOfPostAPI), "Status code is not 201");
        Assert.assertEquals(randomTitleString, APIUtils.getBodyOfPostObj(responseOfPostAPI).getTitle(), "Title doesn't match");
        Assert.assertEquals(Integer.parseInt(JsonUtils.getValueFromTestDataJson("/userIdToPostAPI")), APIUtils.getBodyOfPostObj(responseOfPostAPI).getUserId(), "UserId doesn't match");
        Assert.assertEquals(randomBodyString, APIUtils.getBodyOfPostObj(responseOfPostAPI).getBody(), "Body doesn't match");
        Assert.assertTrue(APIUtils.notNullValue(String.valueOf(APIUtils.getBodyOfPostObj(responseOfPostAPI).getId())), "Id isn't present");

        int index = APIUtils.findElementOfListById(responseOfUsers, JsonUtils.getValueFromTestDataJson("/usersIdValue"));
        Assert.assertEquals(HttpStatus.OK, API.getStatusCode(responseOfUsers), "Status code is not 200");
        Assert.assertTrue(APIUtils.isJSONValid(responseOfUsers.getBody()), "List format is not json");
        File file = new File(JsonUtils.getValueFromConfigJson("/pathToJsonFile"));
        Assert.assertTrue(APIUtils.getUsersList(responseOfUsers).get(index).equals(JsonUtils.convertJsonFileToObjectUser(file)));

        Assert.assertEquals(HttpStatus.OK, API.getStatusCode(responseOfUser5), "Status code is not 200");
        Assert.assertTrue(APIUtils.getUsersList(responseOfUsers).get(index).equals(responseOfUser5.getBody()), "Object User don't match by method equals()");
        Assert.assertEquals(responseOfUser5.getBody().hashCode(), APIUtils.getUsersList(responseOfUsers).get(index).hashCode(), "Object User don't match the hashcode");
        try {
            Thread.sleep(timeOfSleep);
        } catch (InterruptedException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
    }

    @AfterMethod
    public void afterTest (ITestResult iTestResult, ITestContext iTestContext) {
        Date startDate = new Date(iTestResult.getStartMillis());
        Date endDate = new Date(iTestResult.getEndMillis());
        databaseUtils.addFieldToTableTest(CreatingDBModel.createModelOfTest(iTestResult.getName(), iTestResult.getStatus(),
               String.valueOf(iTestResult.getMethod().getMethod()),JsonUtils.getValueFromDatabaseConfigJson("/env"), JsonUtils.getValueFromSettingsJson("/browserName"),
                (int) (Math.random()*5+1), (int) (Math.random()*19+1), ConvertUtils.convertDateToTimestep(startDate), ConvertUtils.convertDateToTimestep(endDate)));
    }
}
