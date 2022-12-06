package com.library.step_definitions;

import com.library.pages.BasePage;
import com.library.pages.BookCategoriesMatch;
import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class BookCategoriesMatchStepDefs {

        LoginPage loginPage = new LoginPage();
        BookPage bookPage = new BookPage();

        BookCategoriesMatch bookCategoriesMatch = new BookCategoriesMatch();

        List<String> actualCategoryList;
        List<String> expectedCategoryList;



    @Given("I login as a {string}")
    public void i_login_as_a(String user) {
       loginPage.login(user);
        BrowserUtil.waitFor(2);
    }
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String books) {

        bookCategoriesMatch.navigateModule(books);

    }

    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {
        actualCategoryList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
        System.out.println("actualCategoryList = " + actualCategoryList);

    }

    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {

        DB_Util.runQuery("select name from book_categories");
        expectedCategoryList = DB_Util.getColumnDataAsList(1);
        System.out.println("expectedCategoryList = " + expectedCategoryList);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        Assert.assertEquals(expectedCategoryList, actualCategoryList);
    }


}
