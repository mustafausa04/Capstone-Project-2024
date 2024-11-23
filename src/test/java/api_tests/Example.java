package api_tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Example {
    String baseUrl = "http://crater.primetech-apps.com/";
    String token;
    Response response;

    @Test
    public void craterAppLogin() {
        String endPoint = "api/v1/auth/login";//path
        String userEmail = "entityadmin@primetechschool.com";
        String userPassword = "primetech@school";

        Map<String, String> requestHeaders = new HashMap<>();//For Map also, we need to use the  entire wording, and capitalize the First Letter.
        requestHeaders.put("Content-Type", "application/json");
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("company", "1");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", userEmail);
        requestBody.put("password", userPassword);
        requestBody.put("device_name", "mobile_app");

        response = RestAssured.given()
                .headers(requestHeaders)
                .body(requestBody)
                .when()
                .post(baseUrl + endPoint);
        response.prettyPrint();
        response.then().statusCode(200);
        token = response.path("token");
        System.out.println("Token is : " + token);
    }


    //we will make this test case depends on the "loginToCraterApp" because if we don't do it the testNG will go by
    //alphabetical and it will run this one before the above one which will make it fail because the above one has
    //to run first to generate the token for us so we can use it for the next tes case
    @Test(dependsOnMethods = "craterAppLogin")
    public void create_a_Customer(){
        Faker faker = new Faker();//class

        String endPoint = "api/v1/customers";//Given
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();


        //We will use map to create the headers according to the documentation so we can send them
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-Type", "application/json");
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("Authorization", "Bearer " + token);
        requestHeaders.put("Business", "995c98ce-cdd9-4ef6-b018-9c696cb07e9d");

        //We will use map to create the request body according to the documentation so we can send it
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("customer_business_id", "98ed68ce-6964-4c2a-9430-8a849e373c52");
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("enable_portal", true);
        requestBody.put("billing", new ArrayList<>()); // Empty array
        requestBody.put("shipping", new ArrayList<>()); // Empty array

        //this means i will give you the headers and request body when i do the post request return the response
        //back and save it in the response
        response = RestAssured.given()
                .headers(requestHeaders)
                .body(requestBody)
                .when()
                .post(baseUrl+endPoint);

        //to get the status code
        response.then().statusCode(200);
        //to print the status code
        System.out.println(response.getStatusCode());

        //to print the response body
        response.prettyPrint();//or  response.getBody().prettyPrint();
    }


}