package stepdefinitions;

import io.cucumber.java.en.And;
import org.junit.Assert;
import utilities.DBUtils;

import java.util.List;

public class AddItem_And_Verify_DB_StepDef {

    @And("the item should be added to the database")
    public void the_item_should_be_added_to_the_database() {

        //we will create the query and assign it to String
        //String query = "select * from CraterDBS.items order by created_at desc;";
        String query = "select * from CraterDBS.items WHERE id = 14559 AND name LIKE 'B%' AND description LIKE 'H%' AND price = 1299 AND unit_id IS NOT NULL;";

        //to retrieve the full row or record
        List<String> row = DBUtils.selectRecord(query);

        //to retrieve the item's id number
        String idNumber = DBUtils.selectRecord(query , "id");
        System.out.println("The new item's id number is : " + idNumber );
        Assert.assertTrue(idNumber.startsWith("145"));

        //we will execute the query by calling the method DBUtils.selectRecord(String query, String colName)
        //then we save the return name and print it
        String name = DBUtils.selectRecord(query,"name");
        System.out.println("The new item's name is : " + name);
        Assert.assertTrue(name.equals("Book"));

        String description = DBUtils.selectRecord(query,"description");
        System.out.println("The description is : " + description);
        Assert.assertTrue(description.equals("Harry Potter"));

    }

}