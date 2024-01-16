package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest {
    static String email =  "softwaretesting@gmail.com";
    static String name =  "William Smith";
    UserPojo userPojo = new UserPojo();
    int userId;

    @Test
    public void verifyUserCreatedSuccessfully(){

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender("male");
        userPojo.setStatus("active");

        Response response = given()
                .header("Authorization","Bearer d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post("/users");
        response.then().log().all().statusCode(201);
        int userId= response.body().jsonPath().getInt("id");
        System.out.println(userId);
    }
    @Test
    public void getUserid(){
        Response response = given()
                .header("Authorization","Bearer d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7")
                .when()
                .get("/users/4111362");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void verifyUserUpdateSuccessfully(){
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender("male");
        userPojo.setStatus("inactive");
        Response response = given()
                .header("Authorization","Bearer d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .patch("/users/4111362");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void delete() {
        Response response = given()
                .header("Authorization","Bearer d88d02069e48aedd8e5032e17f21b929e78721f871e7289b0dc925c3571ee4f7")
                .when()
                .delete("/users/4111362");
        response.then().log().all().statusCode(404);
        response.prettyPrint();

    }
}
