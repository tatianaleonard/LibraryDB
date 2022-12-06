package com.library.step_definitions;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AddNewBooksStepsDefs {

    BookPage bookPage = new BookPage();


    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBookBtn.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
        bookPage.bookName.sendKeys(bookName);



    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String ISBN) {
        bookPage.isbn.sendKeys(ISBN);

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        bookPage.year.sendKeys(year);

    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        bookPage.author.sendKeys(author);
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {

        BrowserUtil.selectByVisibleText(bookPage.mainCategoryElement, category);

        }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.submitBtn.click();

    }
    @Then("verify {string} message is displayed")
    public void verify_the_book_has_been_created_message_is_displayed(String expectedMessage) {
        String actualMessage = bookPage.toastMessage.getText();
        Assert.assertEquals(actualMessage,expectedMessage);

    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String string) {





        DB_Util.runQuery("select isbn,name,author from books\n" +
                "where name = 'The Scrum Field Guide' and author = 'Mitch Lacey' and isbn = '11112028'");
        Map<String, String> bookInfo = DB_Util.getRowMap(1);
        String expectedBookName = bookInfo.get("name");
        System.out.println("expectedBookName = " + expectedBookName);
        String expectedAuthorName = bookInfo.get("author");
        String expectedISBN = bookInfo.get("isbn");


        DB_Util.runQuery("select isbn,name,author from books\n" +
                "where name = 'Head First Java' and author = 'Kathy Sierra' and isbn = '10112028';");
        Map<String, String> bookInfo2 = DB_Util.getRowMap(1);
        String expectedBookName2 = bookInfo.get("name");
        System.out.println("expectedBookName2 = " + expectedBookName2);
        String expectedAuthorName2 = bookInfo.get("author");
        String expectedISBN2 = bookInfo.get("isbn");


         // query for two books
         // DB_Util.runQuery("select isbn,name,author from books\n" +
         // "where isbn in ('11112028','10112028')");



    }


}
