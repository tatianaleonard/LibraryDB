package com.library.step_definitions;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class BorrowedBooksNumberStepDefs {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowedBooksNumber;
    String expectedBorrowedBooksNumber;

    @Given("user login as a {string}")
    public void user_login_as_a_librarian(String user) {
        loginPage.login(user);
        BrowserUtil.waitFor(2);

    }
    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
       actualBorrowedBooksNumber = dashBoardPage.borrowedBooksNumber.getText();
       System.out.println("actualBorrowedBooksNumber = " + actualBorrowedBooksNumber);
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("select count(*) as borrowedBooks from users u inner join book_borrow b on u.id = b.user_id where is_returned = 0");
       expectedBorrowedBooksNumber = DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedBorrowedBooksNumber = " + expectedBorrowedBooksNumber);

        Assert.assertEquals(expectedBorrowedBooksNumber,actualBorrowedBooksNumber);
    }


}
