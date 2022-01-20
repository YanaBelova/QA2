package DatabaseModels;

import java.sql.Timestamp;

public class DBModelOfTableTest {
    private int id;
    private String name;
    private int status_id;
    private String method_name;
    private int project_id;
    private int session_id;
    private Timestamp start_time;
    private Timestamp end_time;
    private String env;
    private String browser;
    private int author_id;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setStatus_id(int status_id){
        this.status_id = status_id;
    }
    public int getStatus_id(){
        return status_id;
    }
    public void setMethod_name(String method_name){
        this.method_name = method_name;
    }
    public String getMethod_name(){
        return method_name;
    }
    public void setProject_id(int project_id){
        this.project_id = project_id;
    }
    public int getProject_id(){
        return project_id;
    }
    public void setSession_id(int session_id){
        this.session_id = session_id;
    }
    public int getSession_id(){
        return session_id;
    }
    public void setStart_time(Timestamp start_time){
        this.start_time=start_time;
    }
    public Timestamp getStart_time(){
        return start_time;
    }
    public void setEnd_time(Timestamp end_time){
        this.end_time = end_time;
    }
    public Timestamp getEnd_time(){
        return end_time;
    }
    public void setEnv(String env){
        this.env = env;
    }
    public String getEnv(){
        return env;
    }
    public void setBrowser(String browser){
        this.browser = browser;
    }
    public String getBrowser(){
        return browser;
    }
    public void setAuthor_id(int author_id){
        this.author_id = author_id;
    }
    public int getAuthor_id(){
        return author_id;
    }
}
