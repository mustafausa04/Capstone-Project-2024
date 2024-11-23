package api_tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CraterAPITest_AddCustomer {

    //this is another API ex so first thing you need to do is to go look at the documentation or AC in the website
    //   https://docs.crater.financial/invoices then lets choose (list all invoices), you will see it is a GET
    //method and in the headers you will see (Authorization: Bearer {token}) so we need to get the token first and
    //the only way is use the log in in  order to get that token so we can save it and keep using it anywhere we want

    //we will make those global so we can use them anywhere we want
    String baseUrl = "http://crater.primetech-apps.com/";//base url
    Response response;//we will make the response global so we can call it anywhere
    String token;//we will make the token global
    int item_id;

    @Test//make sure you use the import from testng option not the junit option
    public void loginToCraterApp(){
        //in this login test we will use these documentation requirements
        //method POST, url "http://crater.primetech-apps.com/"
        //end point "api/v1/auth/login"
        //headers Content-Type", "application/json, "Accept", "application/json", "company", "1"
        //request body "username" "dummy@primetechschool.com", "password" "primetech@school",
        // "device_name", "mobile_app"

        String endpoint ="api/v1/auth/login";//we will get this endpoint from the documentation
        String userEmail ="dummy@primetechschool.com";//we will enter this
        String userPassword = "primetech@school";//we will enter this

        //We will use map to create the headers according to the documentation so we can send them
        //note: if we send those headers the server will complain because data transfer over servers through json
        //or xml format but RestAssured library will take care of that and it will send it as json data format
        Map<String, Object> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-Type", "application/json");
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("company", "1");

        //We will use map to create the request body according to the documentation so we can send it
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", userEmail);
        requestBody.put("password", userPassword);
        requestBody.put("device_name", "mobile_app");//this is also in Ac documentation so we added it here

        //this means i will give you the headers and request body when i do the post request return the response
        //back and save it in the response
        response = RestAssured.given()//this should be Response response = RestAssured.given() but we made it global
                .headers(requestHeaders)//sending the headers
                .body(requestBody)//sending the request body
                .when()//optional
                .post(baseUrl+endpoint);//appending the base url to the end point using POST according to AC

        //to get the status code
        response.then().statusCode(200);
        //to print the status code
        System.out.println(response.getStatusCode());

        //to print the response body
        response.prettyPrint();//or  response.getBody().prettyPrint();

        //to get the token we use path() to get the value of the token and print it, we need the token to save it
        // because later when we do another API test it will ask for the token to be add it to the other requests
        //in the requirement
        token = response.path("token");//the token is a String we put global so we can use anywhere we want
        System.out.println("Your token is : " + token);
    }


    //we will make this test case depends on the "loginToCraterApp" because if we don't do it the testNG will go by
    //alphabetical and it will run this one before the above one which will make it fail because the above one has
    //to run first to generate the token for us so we can use it for the next tes case
    @Test(dependsOnMethods = "loginToCraterApp")
    public void create_a_customer(){
        //to get the documentation here go to https://docs.crater.financial/#api-reference , scroll down to (items)
        //then (create an item)
        //we added the faker dependency to pom.xml to generate fake names and other to us
        Faker faker = new Faker();//create object for Faker class to access its methods
        String endPoint = "api/v1/customers";
        String customerName = faker.name().fullName();
        String customerEmail = faker.internet().emailAddress();

        //We will use map to create the headers according to the documentation so we can send them
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Content-Type", "application/json");
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("Authorization", "Bearer " + token);
        requestHeaders.put("Business", "995c98ce-cdd9-4ef6-b018-9c696cb07e9d");

        //We will use map to create the request body according to the documentation so we can send it
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("customer_business_id", "98ed68ce-6964-4c2a-9430-8a849e373c52");//this required it says on the documentation
        requestBody.put("name", customerName);//this is just an option to add using faker class to generate name
        requestBody.put("email", customerEmail);//this is just an option to add using faker class to generate email

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

        //to get the id for the item that we created, we will declare it on top to make it global so we can use it
        //in the next test case when we do update on the item using PUT request
        item_id = response.path("data.id");//here we will get the id
        System.out.println("The item id that was created is --> "+ item_id);//here we will print the id
    }


//----------------------DELETE ex but it is not gonna work because it is been blocked by the school
// We need access to perform a delete operation (admin user )
//    @Test(dependsOnMethods = "get_specific_Invoice")
//    public void delete_specific_Invoice(){
//        String endpoint = "api/v1/customers/" + item_id;    //.get(0);
//        System.out.println("Full endpoint is " + baseUrl+endpoint);
//
//        Map<String, Object> requestHeaders = new HashMap<>();
//        requestHeaders.put("Content-Type", "application/json");
//        requestHeaders.put("Accept", "application/json");
//        requestHeaders.put("Authorization", "Bearer " + token);
//
//
//        response = RestAssured.given()
//                .headers(requestHeaders)
//                .when()
//                .delete(baseUrl+endpoint);
//
//        response.then().statusCode(200);
//
//        response.prettyPrint();
//    }


}
