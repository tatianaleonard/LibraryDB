package com.library.step_definitions;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class BookInformationMatchStepDefs {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    BookPage bookPage = new BookPage();

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
        loginPage.login(user);
        BrowserUtil.waitFor(2);

    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        dashBoardPage.navigateModule(moduleName);

    }
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        BrowserUtil.waitForVisibility(bookPage.search, 2).sendKeys(bookName);

    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
       //bookPage.editBookBtn.click();
        BrowserUtil.waitForClickablility(bookPage.editBook("Clean Code"), 2).click();

    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        String actualBookName = bookPage.bookName.getAttribute("value");
        System.out.println("actualBookName = " + actualBookName);
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualISBN=bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDesc = bookPage.description.getAttribute("value");

        DB_Util.runQuery("select name,isbn,author,description,year from books where name='Clean Code' and description = 'this book is awesome'");
        Map<String, String> bookInfo = DB_Util.getRowMap(1);
        String expectedBookName = bookInfo.get("name");
        System.out.println("expectedBookName = " + expectedBookName);
        String expectedAuthorName = bookInfo.get("author");
        String expectedISBN = bookInfo.get("isbn");
        String expectedYear = bookInfo.get("year");
        String expectedDesc = bookInfo.get("description");

        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedAuthorName,actualAuthorName);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedDesc,actualDesc);

    }


}
