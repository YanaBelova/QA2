package com.vk.tests;

import com.vk.api.APIRequestFields;
import com.vk.api.models.APIPhotoModel;
import com.vk.api.models.APIPostModel;
import com.vk.api.models.APIUploadServerModel;
import com.vk.forms.AutorizationForm;
import com.vk.forms.MyPageForm;
import com.vk.forms.NewsPageForm;
import com.vk.utils.*;
import com.vk.utils.configuration.ConfigValuesConfiguration;
import com.vk.utils.configuration.TestDataValuesConfiguration;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.util.Map;

public class TestCase1 extends BaseTest {
    private AutorizationForm autorizationForm = new AutorizationForm();
    private NewsPageForm newsPage = new NewsPageForm();
    private VK_APIUtils vk_apiUtils = new VK_APIUtils(TestDataValuesConfiguration.userID);
    private final int lengthOfRandomText = 10;
    private final int differenceBetweenPostIDAndCommentID = 1;
    private final long timeOfWait = 10000;
    private final int theXCoordinateToScrollThePage = 0;
    private final int theYCoordinateToScrollThePage = 550;
    private final String attributeId = "id";
    private final String jsonArrayName = "response";
    private final String fileSuffix = "jpeg";
    private MyPageForm myPageForm = new MyPageForm();

    @Test
    public void testVK() {
        stepLogger(1, "Перейти на сайт https://vk.com/");
        autorizationForm.inputEmailOrPhone(ConfigValuesConfiguration.emailOrPhone);
        autorizationForm.inputPassword(ConfigValuesConfiguration.password);
        autorizationForm.clickSignInButton();
        stepLogger(2, "Авторизация");
        newsPage.clickMyPageButton();
        stepLogger(3, " Перейти на \"Мою страницу\"");
        String randomText = RandomUtils.randomString(lengthOfRandomText);
        HttpResponse<APIPostModel> createPostApi = vk_apiUtils.createPost(randomText);
        stepLogger(4, "Создана запись со случайно сгенерированным текстом на стене");
        myPageForm.setPostID(Integer.parseInt(createPostApi.getBody().getPost_id()));
        WaitUtils.implicitlyWait(timeOfWait);

        Assert.assertEquals(randomText, myPageForm.textFromPost(), "post text is incorrect");
        Assert.assertEquals(String.format("%s%s%s%s", TestDataValuesConfiguration.wpt, TestDataValuesConfiguration.userID, "_",createPostApi.getBody().getPost_id()),
                        myPageForm.authorOfPost(), "the author of the post is incorrect");
        stepLogger(5, "На стене появилась запись с нужным текстом от правильного пользователя");

        File photoToUpload = FileUtils.convertFile(ConfigValuesConfiguration.pathToFile, ConfigValuesConfiguration.fileName, fileSuffix);
        String randomNewText = RandomUtils.randomString(lengthOfRandomText);
        File[] valueArray = {photoToUpload};
        String[] keyArray ={APIRequestFields.PHOTO.getField()};
        Map<String, Object> valuesMap = MapUtils.getMap(keyArray, valueArray);
        HttpResponse<APIUploadServerModel> getWallUploadServerApi = vk_apiUtils.getWallUploadServer();
        HttpResponse<APIPhotoModel> fileTransferApi = vk_apiUtils.fileTransfer(getWallUploadServerApi.getBody().getUploadUrl(), valuesMap);
        HttpResponse<JsonNode> saveUploadPhotoApi = vk_apiUtils.saveUploadPhoto(fileTransferApi.getBody().getServer(), fileTransferApi.getBody().getPhoto(),
                fileTransferApi.getBody().getHash());
        String photoID = JsonUtils.getValueFromJsonArray(saveUploadPhotoApi.getBody().toString(), jsonArrayName, attributeId);
        vk_apiUtils.editPost(createPostApi.getBody().getPost_id(), randomNewText, photoID);
        stepLogger(6, "Отредактировать запись через запрос к API - изменить текст и добавить (загрузить) любую картинку");
        String textFromPost =myPageForm.textFromPost();

        Assert.assertEquals(randomNewText, textFromPost, "post edit text is incorrect");
        Assert.assertEquals(String.format("%s%s%s", TestDataValuesConfiguration.userID,
                "_",photoID), myPageForm.photoID(), "Photo incorrect");
        stepLogger(7, "Изменился текст сообщения и добавилась загруженная картинка");

        String randomCommentText = RandomUtils.randomString(lengthOfRandomText);
        vk_apiUtils.createComment(createPostApi.getBody().getPost_id(), randomCommentText);
        stepLogger(8, "Добавить комментарий к записи со случайным текстом");
        int commentID = Integer.parseInt(createPostApi.getBody().getPost_id())+ differenceBetweenPostIDAndCommentID;
        myPageForm.scrollWindow(theXCoordinateToScrollThePage, theYCoordinateToScrollThePage);
        WaitUtils.implicitlyWait(timeOfWait);
        myPageForm.clickShowCommentButton();

        Assert.assertEquals(String.format("%s%s%s%s", TestDataValuesConfiguration.wpt,
                        TestDataValuesConfiguration.userID, "_", commentID),
                myPageForm.authorOfComment(),"the author of the comment is incorrect");
        Assert.assertEquals(randomCommentText, myPageForm.textFromComment(),"the comment text is incorrect");
        stepLogger(9, "К нужной записи добавился комментарий от правильного пользователя");

        myPageForm.clickLike();
        stepLogger(10, "Поставить лайк записи");

        Assert.assertTrue(myPageForm.checkLike(vk_apiUtils.requestLikesIsLiked(createPostApi.getBody().getPost_id()).getBody().getLiked()),"no like");
        stepLogger(11, "У записи появился лайк от правильного пользователя");

        vk_apiUtils.deletePost(createPostApi.getBody().getPost_id());
        stepLogger(12, "Удалить созданную запись");
        WaitUtils.implicitlyWait(timeOfWait);
        boolean checkDelete = myPageForm.checkDelete();

        Assert.assertFalse(checkDelete, "post not deleted");
        stepLogger(13, "Запись удалена");
        FileUtils.deleteFile(photoToUpload);
    }
}
