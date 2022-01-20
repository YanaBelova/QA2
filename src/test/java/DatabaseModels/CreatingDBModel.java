package DatabaseModels;

import java.sql.Timestamp;

public class CreatingDBModel {
    public static DBModelOfTableTest createModelOfTest(String name, int status_id, String methodName, String env, String browser, int project_id, int session_id, Timestamp start_time, Timestamp end_time){
        DBModelOfTableTest dbModel = new DBModelOfTableTest();
        dbModel.setName(name);
        dbModel.setStatus_id(status_id);
        dbModel.setMethod_name(methodName);
        dbModel.setEnv(env);
        dbModel.setBrowser(browser);
        dbModel.setProject_id(project_id);
        dbModel.setSession_id(session_id);
        dbModel.setStart_time(start_time);
        dbModel.setEnd_time(end_time);
        return dbModel;
    }
}
