package utils;

import aquality.selenium.browser.AqualityServices;
import DatabaseModels.CreatingDBModel;
import DatabaseModels.DBModelOfTableTest;
import databaseMySQL.DataBase;
import databaseMySQL.TableTest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils extends DataBase{

    private String fieldsOfTestTable = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",TableTest.name,",",TableTest.status_id,",",
           TableTest.method_name,",",TableTest.project_id,",",TableTest.session_id,",",TableTest.start_time,",",TableTest.end_time,",",TableTest.env,",",TableTest.browser);

    public DBUtils(String url, String userName, String password){
        super(url,userName,password);
    }
    public void copyFieldsWithAGivenID(List<DBModelOfTableTest> listOfModels, List<Integer> listOfId){
        for (int i =0; i<listOfId.size(); i++){
            copyFieldOfTableTest(listOfId.get(i), listOfModels.get(i));
        }
    }

    public List<DBModelOfTableTest> getListOfModelsWithTwoIdenticalNumbers(List<DBModelOfTableTest> model, int randomNumber){
        List<DBModelOfTableTest> listOfModels = new ArrayList<>();
        for (DBModelOfTableTest i : model){
            if((i.getId() % 10 == randomNumber && (i.getId() - (i.getId() % 10))/10 == randomNumber) ||
                    (i.getId()%10 == randomNumber && (i.getId() - (i.getId() % 100))/100 == randomNumber) ||
                    (((i.getId() - (i.getId() % 100))/100 == randomNumber) && (i.getId() % 100 - (i.getId() %10))/10 == randomNumber) ||
                    ((i.getId() % 100 - (i.getId() %10))/10 == randomNumber)&& (i.getId()%10 == randomNumber)) {
                listOfModels.add(CreatingDBModel.createModelOfTest(i.getName(), i.getStatus_id(),JsonUtils.getValueFromDatabaseConfigJson("/methodName"),
                        JsonUtils.getValueFromDatabaseConfigJson("/env"),i.getBrowser(), i.getProject_id(), i.getSession_id(),i.getStart_time(),i.getEnd_time()));
                if (listOfModels.size()>9)
                    break;
            }
        }
        return listOfModels;
    }

    public List<Integer> getListOfIdWithTwoIdenticalNumbers(List<DBModelOfTableTest> model, int randomNumber){
        List<Integer> listOfId = new ArrayList<>();
        for (DBModelOfTableTest i : model){
            if((i.getId() % 10 == randomNumber && (i.getId() - (i.getId() % 10))/10 == randomNumber) ||
                    (i.getId()%10 == randomNumber && (i.getId() - (i.getId() % 100))/100 == randomNumber) ||
                    (((i.getId() - (i.getId() % 100))/100 == randomNumber) && (i.getId() % 100 - (i.getId() %10))/10 == randomNumber) ||
                    ((i.getId() % 100 - (i.getId() %10))/10 == randomNumber)&& (i.getId()%10 == randomNumber)) {
                listOfId.add(i.getId());
                if (listOfId.size()>9)
                    break;
            }
        }
        return listOfId;
    }

    public List<DBModelOfTableTest> getListOfModelsOfTheTableTest() {
        DBModelOfTableTest model = null;
        List<DBModelOfTableTest> dbModelsList = new ArrayList<DBModelOfTableTest>();
       ResultSet resultSet = read(JsonUtils.getValueFromDatabaseConfigJson("/nameOfTable"),
               String.format("%s%s%s",TableTest.id, ",",fieldsOfTestTable));
        try {
            while (resultSet.next()) {
                model = new DBModelOfTableTest();
                model.setId(resultSet.getInt(String.valueOf(TableTest.id)));
                model.setName(resultSet.getString(String.valueOf(TableTest.name)));
                model.setStatus_id(resultSet.getInt(String.valueOf(TableTest.status_id)));
                model.setMethod_name(resultSet.getString(String.valueOf(TableTest.method_name)));
                model.setProject_id(resultSet.getInt(String.valueOf(TableTest.project_id)));
                model.setSession_id(resultSet.getInt(String.valueOf(TableTest.session_id)));
                model.setStart_time(resultSet.getTimestamp(String.valueOf(TableTest.start_time)));
                model.setEnd_time(resultSet.getTimestamp(String.valueOf(TableTest.end_time)));
                model.setEnv(resultSet.getString(String.valueOf(TableTest.env)));
                model.setBrowser(resultSet.getString(String.valueOf(TableTest.browser)));
                model.setAuthor_id(resultSet.getInt(String.valueOf(TableTest.author_id)));
                dbModelsList.add(model);
            }
                } catch (SQLException ex) {
            AqualityServices.getLogger().error(String.valueOf(ex));
                }
                return dbModelsList;
        }

    public void deleteFieldOfTableTest(DBModelOfTableTest model){
        delete(JsonUtils.getValueFromDatabaseConfigJson("/nameOfTable"), String.valueOf(TableTest.env), String.format("%s%s%s","'",model.getEnv(),"'"));
    }

    public void deleteFieldsOfTableTest(List<DBModelOfTableTest> listOfModels){
        for(DBModelOfTableTest model : listOfModels){
            deleteFieldOfTableTest(model);
        }
    }

    public void copyFieldOfTableTest(int idIndex, DBModelOfTableTest model){
        copy(JsonUtils.getValueFromDatabaseConfigJson("/nameOfTable"), fieldsOfTestTable,
                String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", "'",model.getName(),"',", TableTest.status_id,",",TableTest.method_name,",",TableTest.project_id,",",
                        TableTest.session_id,",",TableTest.start_time,",",TableTest.end_time,",'",model.getEnv(),"',",TableTest.browser), String.valueOf(TableTest.id), idIndex);
    }

    public void addFieldToTableTest(DBModelOfTableTest model){
        setForeignKeysOff();
        create(JsonUtils.getValueFromDatabaseConfigJson("/nameOfTable"),
                fieldsOfTestTable,String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", "'",
                        model.getName(),  "'," ,model.getStatus_id(),",'",
                        model.getMethod_name(),"',",model.getProject_id(),
                        ",", model.getSession_id(),",'",model.getStart_time(),
                        "','",model.getEnd_time(),"','",model.getEnv(),"','",model.getBrowser(),"'"));
    }

    public void simulationOfRunningAutotest(String columnName, Object value){
        update(JsonUtils.getValueFromDatabaseConfigJson("/nameOfTable"), columnName,  value, String.valueOf(TableTest.env), String.format("%s%s%s","'",JsonUtils.getValueFromDatabaseConfigJson("/env"),"'"));
    }

    public List<Integer> getListOfStatusIdWhereEnvIsYanix(){
        List<Integer> listOfStatusId = new ArrayList<>();
        List<DBModelOfTableTest> list = getListOfModelsOfTheTableTest();
        for (DBModelOfTableTest m : list){
            if(m.getEnv().equals(JsonUtils.getValueFromDatabaseConfigJson("/env"))){
                listOfStatusId.add(m.getStatus_id());
            }
        }
        return listOfStatusId;
    }

    public List<Timestamp> getListOfStartTimeWhereEnvIsYanix(){
        List<Timestamp> listOfTime = new ArrayList<>();
        List<DBModelOfTableTest> list = getListOfModelsOfTheTableTest();
        for (DBModelOfTableTest m : list){
            if(m.getEnv().equals(JsonUtils.getValueFromDatabaseConfigJson("/env"))){
                listOfTime.add(m.getStart_time());
            }
        }
        return listOfTime;
    }

    public boolean checkPassingTests(List<Integer> listOfStatusId, int statusId){
        for(int i : listOfStatusId){
            if(i == statusId)
                return true;
        }
        return false;
    }

    public boolean checkInformationUpdate(List<Timestamp> listOfTime, Timestamp time){
        for(Timestamp i : listOfTime){
            if(i.getTime() == time.getTime()/1000 * 1000) {
                return true;
            }
        }
        return false;
    }

    public boolean dataDeletionCheck(List<Integer> listOfId){
      return listOfId.size() == 0;
    }
}
