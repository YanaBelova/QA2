package utils;

import java.sql.Timestamp;
import java.util.Date;

public class ConvertUtils {

    public static Timestamp convertDateToTimestep(Date date){
        return new java.sql.Timestamp(date.getTime());
    }

}
