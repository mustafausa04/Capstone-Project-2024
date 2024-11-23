package api_tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import utilities.DBUtils;

public class CraterAPITest_AddCustomer_Verify_DB {

    @Test
    public void verifyCustomerOnDataBase(){
        //we will create the query and assign it to String
        //String query = "select * from CraterDBS.items order by created_at desc;";
        String query = "select * from CraterDBS.customers WHERE id = 5106 AND name LIKE 'K%';";

        //to retrieve the customer's id number
        String customerId = DBUtils.selectRecord(query , "id");
        System.out.println("The new customer's id number is : " + customerId );
        Assert.assertTrue(customerId.startsWith("51"));

        //we will execute the query by calling the method DBUtils.selectRecord(String query, String colName)
        //then we save the return name and print it
        String name = DBUtils.selectRecord(query,"name");
        System.out.println("The customer's name is : " + name);
        Assert.assertTrue(name.equals("Kenny Schmeler"));

    }

}
