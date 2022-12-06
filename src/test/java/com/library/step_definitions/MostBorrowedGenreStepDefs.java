package com.library.step_definitions;

import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class MostBorrowedGenreStepDefs {
    String expectedGenre;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.runQuery("select bc.name,count(*) from book_borrow bb\n" +
                "inner  join books b on bb.book_id = b.id\n" +
                "inner join book_categories bc on b.book_category_id=bc.id\n" +
                "group by name\n" +
                "order by 2 desc");
      expectedGenre = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedGenre = " + expectedGenre);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String bookGenre) {

        String actualGenre = bookGenre;
        System.out.println("actualGenre = " + actualGenre);
        Assert.assertEquals(expectedGenre, actualGenre);
    }


}
