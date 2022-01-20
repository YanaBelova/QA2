package com.vk.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.forms.Form;
import com.vk.utils.configuration.TestDataValuesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MyPageForm extends Form {
    private int postID;
    private String attributeId = "id";
    private String attributeDataPhotoId= "data-photo-id";
    private int responseWhenLikeIsSet = 1;
    private Post post = new Post();

    public MyPageForm() {
        super(By.className("current_info"), "My page");
    }
    public void setPostID(int postID){
        this.postID = postID;
    }
    public Post getPost(){
        return this.post;
    }

    public void scrollWindow(int x, int y){
        JavascriptExecutor jse = AqualityServices.getBrowser().getDriver();
        jse.executeScript(String.format("%s%s%s%s%s", "window.scrollBy(",x,",",y,")"));
    }
        public void clickLike(){
            post.findLike(postID).click();
        }

        public String photoID(){
        return post.findPhoto(postID).getAttribute(attributeDataPhotoId);
        }

        public String textFromPost(){
        return post.getPost(postID).getText();
        }

        public String authorOfPost(){
            return post.getPost(postID).getAttribute(attributeId);
        }

        public String authorOfComment(){return post.getComment(postID).getAttribute(attributeId);}

        public String textFromComment(){
            return post.getComment(postID).getText();
       }

        public boolean checkDelete(){
            return post.getPost(postID).isDisplayed();
        }

        public boolean checkLike(int response){
            return response == responseWhenLikeIsSet;
        }

        public void clickShowCommentButton(){post.findShowCommentButton(postID).click();}

    public class Post extends Form {

        public Post() {
            super(By.className("PostHeaderActionsButtonMoreIcon"), "edit post button");
        }

        public WebElement getPost(Integer postID){
            return AqualityServices.getBrowser().getDriver().findElement(
                    By.xpath(String.format("%s%s%s%s%s", "//div[@id='post", TestDataValuesConfiguration.userID, "_", postID, "']//div[contains(@class,'_wall_post_cont')]")));
        }

        public WebElement getComment(Integer postID){
            return AqualityServices.getBrowser().getDriver().findElement(
                    By.xpath(String.format("%s%s%s%s%s", "//div[@id='post", TestDataValuesConfiguration.userID,"_",postID,"']//div[@class='reply_text']//div")));
        }

        public WebElement findPhoto(Integer postID){
            return AqualityServices.getBrowser().getDriver().findElement(
                    By.xpath(String.format("%s%s%s%s%s", "//div[@id='post", TestDataValuesConfiguration.userID,"_",postID,"']//div[contains(@class,'_wall_post_cont')]//a")));
        }

        public WebElement findLike(Integer postID){
            return AqualityServices.getBrowser().getDriver().findElement(
                    By.xpath(String.format("%s%s%s%s%s", "//div[@id='post", TestDataValuesConfiguration.userID,"_",postID,"']//span[@class='PostBottomAction__icon _like_button_icon']")));
        }

        public WebElement findShowCommentButton(Integer postID){
            return AqualityServices.getBrowser().getDriver().findElement(
                    By.xpath(String.format("%s%s%s%s%s","//div[@id='post", TestDataValuesConfiguration.userID,"_",postID,"']//span[@class='js-replies_next_label']")));

        }
    }
}
