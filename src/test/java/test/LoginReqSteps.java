package test;

import io.restassured.authentication.OAuthScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import static org.hamcrest.Matchers.equalTo; ///DI SINI BUAT  MAKE EQUALTO

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


//import auth
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginReqSteps {
    public void halamanAwal(){
        String url = "https://reqres.in";
        Assert.assertTrue(url.equals("https://reqres.in"));
    }
    public void klikLogin(){
        RestAssured.baseURI = "https://reqres.in";
        Map<String, Object> reqBody =new HashMap<>();
        reqBody.put("email","eve.holt@reqres.in");
        reqBody.put("password","cityslicka");
        //otentifikasi dengan 2 cara
        //[]CARA PERTAMA
        //given().auth().basic("","");
        //[]CARA KE-2
        //PreemptiveBasicAuthScheme cool = new PreemptiveBasicAuthScheme();
        //cool.setUserName("Admin");
        //cool.setPassword("Admin");
        //RestAssured.authentication = cool;
        //Sampe sini
        SerenityRest
                .given()
                    .contentType("application/JSON")
                    .body(reqBody)
                    //.auth().basic("Admin","Admin") //atau di sini
                    //.auth().oauth2("3HrghbVeDUQWaOriqrXYLZmCb4cEXB") //bearer token
                .when()
                    .post("/api/login")
                .then()
                    .statusCode(200)
                    .body("token",equalTo("QpwL5tke4Pnpja7X4"));
        //String tokens = SerenityRest.then().extract().path("token");
        //Assert.assertTrue(tokens.equals("QpwL5tke4Pnpja7X4"));
        SerenityRest
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/loginUser.json"));
    }
    public void getData(){
        ValidatableResponse body = SerenityRest
                .given()
                    .contentType("application/JSON")
                .when()
                    .log().all()
                    .get("https://reqres.in/api/unknown/2")
                .then()
                    .statusCode(200);
        int id = SerenityRest.then().extract().path("data.id");
        Assert.assertTrue(id==2);
        String name = SerenityRest.then().extract().path("data.name");
        Assert.assertTrue(name.equals("fuchsia rose"));
        int tahun = SerenityRest.then().extract().path("data.year");
        Assert.assertTrue(tahun==2001);
        String color = SerenityRest.then().extract().path("data.color");
        Assert.assertTrue(color.equals("#C74375"));
        String pantone_value = SerenityRest.then().extract().path("data.pantone_value");
        Assert.assertTrue(pantone_value.equals("17-2031"));
        SerenityRest
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/getUser.json"));
    }

    public void klikRegister(){
        Map<String, Object> reqBody =new HashMap<>();
        reqBody.put("email","eve.holt@reqres.in");
        reqBody.put("password","pistol");

        SerenityRest
                .given()
                    .contentType("application/JSON")
                    .body(reqBody)
                .when()
                    .post("https://reqres.in/api/register")
                .then()
                    .statusCode(200);
        String tokens = SerenityRest.then().extract().path("token");
        Assert.assertTrue(tokens.equals("QpwL5tke4Pnpja7X4"));
        int id = SerenityRest.then().extract().path("id");
        Assert.assertTrue(id==4);
        SerenityRest
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/registerUser.json"));
    }

    public void getFriend(){
        ValidatableResponse body = SerenityRest
                .given()
                    .contentType("application/JSON")
                .when()
                    .log().all()
                    .get("https://reqres.in/api/users?delay=3")
                .then()
                    .statusCode(200);
        int page = SerenityRest.then().extract().path("page");
        Assert.assertTrue(page==1);
        int per_page = SerenityRest.then().extract().path("per_page");
        Assert.assertTrue(per_page == 3);
        int total = SerenityRest.then().extract().path("total");
        Assert.assertTrue(total == 12);
        int total_pages = SerenityRest.then().extract().path("total_pages");
        Assert.assertTrue(total_pages==4);

        int data1id = SerenityRest.then().extract().path("data[0].id");
        Assert.assertTrue(data1id==1);
        String data1email = SerenityRest.then().extract().path("data[0].email");
        Assert.assertTrue(data1email.equals("george.bluth@reqres.in"));
        String data1first = SerenityRest.then().extract().path("data[0].first_name");
        Assert.assertTrue(data1first.equals("George"));
        String data1last = SerenityRest.then().extract().path("data[0].last_name");
        Assert.assertTrue(data1last.equals("Bluth"));
        String data1ava = SerenityRest.then().extract().path("data[0].avatar");
        Assert.assertTrue(data1ava.equals("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"));
        SerenityRest
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/getFriend.json"));
    }
}
