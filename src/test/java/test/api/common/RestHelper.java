package test.api.common;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestHelper {

    public static String get(String uri) {
        Response response = given().contentType("application/json").
                get(uri).then().contentType("application/json").extract().response();
        String jsonAsString = response.asString();
        return jsonAsString;
    }


    public static Response getRes(String uri) {
        Response response = given().relaxedHTTPSValidation().contentType("application/json")
                .get(uri).then().log().all().extract().response();
        return response;
    }
}
