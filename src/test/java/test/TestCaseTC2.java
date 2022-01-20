package test;

import databaseMySQL.TableTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConvertUtils;
import utils.DBUtils;
import utils.JsonUtils;
import java.sql.Timestamp;
import java.util.Date;

public class TestCaseTC2 {
    private final Timestamp timestampDate = ConvertUtils.convertDateToTimestep(new Date());
    private DBUtils databaseUtils = new DBUtils(JsonUtils.getValueFromDatabasePasswordJson("/url"),JsonUtils.getValueFromDatabasePasswordJson("/username"),
            JsonUtils.getValueFromDatabasePasswordJson("/password"));
    private final int repeatingNumberInId = (int) (Math.random()*8 +1);
    @Test
    public void testTC2(){
        databaseUtils.copyFieldsWithAGivenID(databaseUtils.getListOfModelsWithTwoIdenticalNumbers(databaseUtils.getListOfModelsOfTheTableTest(), repeatingNumberInId),
                databaseUtils.getListOfIdWithTwoIdenticalNumbers(databaseUtils.getListOfModelsOfTheTableTest(), repeatingNumberInId));
        databaseUtils.simulationOfRunningAutotest(String.valueOf(TableTest.status_id), Integer.parseInt(JsonUtils.getValueFromDatabaseConfigJson("/simulatedStatus_id")));
        Assert.assertTrue(databaseUtils.checkPassingTests(databaseUtils.getListOfStatusIdWhereEnvIsYanix(),Integer.parseInt(JsonUtils.getValueFromDatabaseConfigJson("/simulatedStatus_id"))),
                "Tests are not passed");
        databaseUtils.simulationOfRunningAutotest(String.valueOf(TableTest.start_time), String.format("%s%s%s","'", timestampDate,"'"));
        Assert.assertTrue(databaseUtils.checkInformationUpdate(databaseUtils.getListOfStartTimeWhereEnvIsYanix(), timestampDate), "Information in tests are not update");
        databaseUtils.deleteFieldsOfTableTest(databaseUtils.getListOfModelsWithTwoIdenticalNumbers(databaseUtils.getListOfModelsOfTheTableTest(), repeatingNumberInId));
        Assert.assertTrue(databaseUtils.dataDeletionCheck(databaseUtils.getListOfStatusIdWhereEnvIsYanix()), "Information in the database has not been deleted");
    }
}
