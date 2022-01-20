package databaseMySQL;

import aquality.selenium.browser.AqualityServices;
import utils.JsonUtils;

import java.sql.*;

public class DataBase {
    private final String url;
    private final String userName;
    private final String password;

    protected DataBase(String url, String userName, String password){
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public Connection startConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        }catch(Exception e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
        return connection;
    }

    public Statement startStatement(Connection connection){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        }catch(Exception e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
        return statement;
    }

   public void closeConnection(Connection connection){
    try {
        connection.close();
    } catch (SQLException se) {
        AqualityServices.getLogger().error(String.valueOf(se));
    }
}
    public void closeStatement(Statement statement){
        try {
            statement.close();
        } catch (SQLException se) {
            AqualityServices.getLogger().error(String.valueOf(se));
        }
    }

    public void copy(String nameOfTable, String fieldsOfTable, String copyFields, String fieldName, Object fieldValue){
        Connection  connection= startConnection();
        Statement statement = startStatement(connection);
        try {
            statement.executeUpdate(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                    DataBaseCommands.insert," ",DataBaseCommands.into," ", nameOfTable,
                    "(", fieldsOfTable,") ",DataBaseCommands.select," ",
                    copyFields," ", DataBaseCommands.from," ",nameOfTable,
                    " ", DataBaseCommands.where," ",fieldName," = " , fieldValue));
        } catch (SQLException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }finally {
            closeConnection(connection);
            closeStatement(statement);
        }
    }
    public void delete( String nameOfTable, String columnName, Object value){
        Connection  connection= startConnection();
        Statement statement = startStatement(connection);
        try {
            statement.executeUpdate(String.format("%s%s%s%s%s%s%s%s%s%s%s", DataBaseCommands.delete," ",DataBaseCommands.from, " ",nameOfTable ,
                    " ", DataBaseCommands.where," ",columnName," = ", value));
        } catch (SQLException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }finally {
            closeConnection(connection);
            closeStatement(statement);
        }
    }
    public void update(String nameOfTable , String columnName, Object value,String columnName1, Object value1){
        Connection  connection= startConnection();
        Statement statement = startStatement(connection);
        try {
            statement.executeUpdate(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",DataBaseCommands.update, " ",
                    nameOfTable," ",DataBaseCommands.set," ", columnName, " = ", value,
                    " ", DataBaseCommands.where," ", columnName1," = ", value1));
        } catch (SQLException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }finally {
            closeConnection(connection);
            closeStatement(statement);
        }
    }

    public void create(String nameOfTable ,String fieldsOfTable, String fieldsValues){
        Connection  connection= startConnection();
        Statement statement = startStatement(connection);
        try {
            statement.executeUpdate(String.format("%s%s%s%s%s%s%s%s%s%s%s%s", DataBaseCommands.insert," ",DataBaseCommands.into," ",
                    nameOfTable,"(", fieldsOfTable ,") ",DataBaseCommands.values,"(",fieldsValues,");"));
        } catch (SQLException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }finally {
            closeConnection(connection);
            closeStatement(statement);
        }
    }

    public void setForeignKeysOff(){
        Connection  connection= startConnection();
        Statement statement = startStatement(connection);
        try {
            statement.executeUpdate(String.format("%s%s%s%s%s", DataBaseCommands.set, " ", DataBaseCommands.FOREIGN_KEY_CHECKS,"=",DataBaseCommands.OFF));
        }
        catch (SQLException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }finally {
            closeConnection(connection);
            closeStatement(statement);
        }
    }

    public ResultSet read(String nameOfTable, String fieldsOfTable) {
        ResultSet resultSet = null;
        try {
            Connection connection = startConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("%s%s%s",DataBaseCommands.select," ", fieldsOfTable ));
            resultSet = preparedStatement.executeQuery(String.format("%s%s%s%s%s%s", DataBaseCommands.select," ","* ",
                    DataBaseCommands.from," ",nameOfTable));
        } catch (SQLException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
        return resultSet;
    }
}