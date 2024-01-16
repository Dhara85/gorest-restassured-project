package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public void setup() {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page", "1");
        qParam.put("per_page", "20");
        response = given()
                .queryParams(qParam)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    // 1. Verify if the total record is 20
    @Test
    public void test001() {
        response.body("total.size", equalTo(20));
    }
    //2. Verify if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
    @Test
    public void test002() {  // above Id number is not in the list
        response.body("[1].name", equalTo("Ghanaanand Verma"));
    }
    //3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
    @Test
    public void test003() {
        response.body("name", hasItem("Manik Gill"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test004() { // names in the Array List is not as given
        response.body("name", hasItems("Brahmdev Devar", "Mani Banerjee", "Rahul Iyengar"));
    }
    //5. Verify the email of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
    @Test
    public void test005() {
        response.body("find{it.id == 5914060}.email", equalTo("ramaa_banerjee@roob.example"));
    }
    //6. Verify the status is “Active” of user name is “Amaresh Rana”
    @Test
    public void test006() {
        response.body("find{it.name == 'Vaijayanthi Achari'}.status", equalTo("active"));
    }
    //     7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal
    @Test
    public void test007() {
        response.body("find{it.name == 'Mr. Ekaksh Agarwal'}.gender", equalTo("male"));
    }
}
