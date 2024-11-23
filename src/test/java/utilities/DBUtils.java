package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    //this is the host name when we first connect to MySQL we have to add it and we will make it static because
    //we will call them in static method below
    static String dbUrl = "jdbc:mysql://stack-overflow.cfse9bqqndon.us-east-1.rds.amazonaws.com/CraterDBS";
    static String userName = "craterdbuser";
    static String password = "ptschool2023";

    //here we will separate methods
    //1. connection method
    public static Connection getConnection(){
        //this method for connection only and we will surround it with try catch because if the username,
        //password is wrong or dburl wrong will go to the catch section
        try {
            //this will execute the dburl, userName, password and return the connection to the method above
            //getConnection() so we can use it in any other method
            return DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //2. executing query method returning all rows and columns
    //the List<List<String>> it means the method executeQuery will return everything all rows and columns and
    //we will pass the (String query) when we add it in the (DbTestDemo) class
    public  static List<List<String>> executeQuery(String query){
        //we need to declare this code outside the (try catch block) so when we (return dataSet) in the bottom
        //it won't complain
        List<List<String>> dataSet = new ArrayList<>();
        try {
            Connection connection = getConnection();//we call the getConnection()
            Statement statement = connection.createStatement();//writing the query
            ResultSet resultSet = statement.executeQuery(query);//executing the query
            ResultSetMetaData rsmd = resultSet.getMetaData();//saving the data back after executing the query
            int colCount = rsmd.getColumnCount();
            while(resultSet.next()){//to go through all rows
                //System.out.println(resultSet.getString("invoice_number"));
                List<String> row = new ArrayList<>();
                for (int i = 1; i <colCount ; i++) {//to go to all columns in each row
                    //System.out.print(resultSet.getString(i) + "|| ");
                    row.add(resultSet.getString(i));
                }
                dataSet.add(row);
                //System.out.println();
            }
            System.out.println(dataSet);

            //close all connections
            resultSet.close();
            statement.close();
            connection.close();
            return dataSet;

        } catch (SQLException e) {
            System.out.println("Connection not successful");
            e.printStackTrace();
        }
        return dataSet;
    }


    //3. selectRecord() to return the first row only
    //Select query that will return the first record as a list of strings
    public  static List<String> selectRecord(String query){
        List<String> dataSet = new ArrayList<>();
        try {
            Connection connection = getConnection();//we call the getConnection()
            Statement statement = connection.createStatement();//writing the query
            ResultSet resultSet = statement.executeQuery(query);//executing the query
            ResultSetMetaData rsmd = resultSet.getMetaData();//saving the data back after executing the query
            int colCount = rsmd.getColumnCount();
            if(resultSet.next()){//to go to the first row only because we are using (if) not (while)
                for (int i = 1; i < colCount; i++) {//then go to all columns in first row
                    dataSet.add(resultSet.getString(i));
                }
            }
            System.out.println(dataSet);

            //close all connections
            resultSet.close();
            statement.close();
            connection.close();
            return dataSet;

        } catch (SQLException e) {
            System.out.println("Connection not successful");
            e.printStackTrace();
        }
        return dataSet;
    }


    //3. we will select the first record and return back the data for that specific column
    public  static String selectRecord(String query, String colName){
        String  dataSet  = null;
        try {
            Connection connection = getConnection();//to connect to MySQL
            Statement statement = connection.createStatement();//to write the query
            ResultSet resultSet = statement.executeQuery(query);//to execute the query
            ResultSetMetaData rsmd = resultSet.getMetaData();//saving the data back after executing the query
            int colCount = rsmd.getColumnCount();//column numbers saved in int variable
            if(resultSet.next()){//to go to the first row only because we are using (if) not (while)
                dataSet = resultSet.getString(colName);
            }
            System.out.println(dataSet);

            //close all connections
            resultSet.close();
            statement.close();
            connection.close();
            return dataSet;

        } catch (SQLException e) {
            System.out.println("Connection not successful");
            e.printStackTrace();
        }
        return dataSet;
    }



}
