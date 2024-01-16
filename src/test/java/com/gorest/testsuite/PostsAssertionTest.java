package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                // .pathParam("url", "https://gorest.co.in/public/v2")
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .when()
                .get("/posts")
                .then().statusCode(200);
    }


    @Test
    // 1. Verify if the total record is 25
    public void test001() {
        response.body("total.size", equalTo(25));
    }

    @Test
    //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
    //cohaero libero.”
    public void test002() {
        response.body("[2].title", equalTo("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }

    @Test
    //3.Check the single user_id in the Array list (5914249)
    public void test003() {
        response.body("[4].user_id", equalTo(5914249));
    }

    @Test
    //4.Check the multiple ids in the ArrayList (5491425, 5914253,5914249)
    public void test004() {
        response.body("name", hasItems("(5914243, 5914202, 5914199)"));
    }

    @Test
    //5.Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
    //Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
    //vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
    //tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
    //acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
    public void test005() {
        response.body("user_id[0]", equalTo("Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."));
    }
}
