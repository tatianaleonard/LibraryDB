package com.library.step_definitions;

import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class UserInformationStepDefs {
    String expectedResult;
    String actualUniqueIDCount;

    List<String> expectedColumnsNames;


    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection();

    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        DB_Util.runQuery("select count(id) from users");
        expectedResult = DB_Util.getFirstRowFirstColumn();

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
    DB_Util.runQuery("select distinct count(id) from users");
    actualUniqueIDCount = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedResult, actualUniqueIDCount);
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");
        expectedColumnsNames = DB_Util.getAllColumnNamesAsList();
        System.out.println("expectedColumnsNames = " + expectedColumnsNames);
    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> actualColumnsNames) {
        System.out.println("actualColumnsNames = " + actualColumnsNames);
        Assert.assertEquals(expectedColumnsNames,actualColumnsNames );
    }

}
