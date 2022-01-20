package APIModels;

public class Post{
    private int userId;
    private int id;
    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public int getId(){
        return id;
    }

    public String getBody(){
        return body;
    }

    public int getUserId(){
        return userId;
    }

}