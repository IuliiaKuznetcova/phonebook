package api.tests;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ApiTest {
    public final String BASE_URI = "https://reqres.in";

    /*    @Test
        //пример выполнения get-запроса без проверок
        public void getListUser_1() {
            given()                                                         // старт get-запроса
                    .when()                                                 // когда
                    .log().all()                                            // команда для показа структуры get-запроса
                    .get("https://reqres.in/api/users?page=2")         // get-запрос
                    .then()                                                 // тогда
                    .log().all();                                           // ответ, полученный от сервера

        }*/
    @Test
    public void getListUser() {
        given()
                .when()
                .log().all()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all();

    }

    // Пример выполнения get запроса с проверкой полей с помощью TestNg
    @Test
    public void getListUser_2() {
        Response response = given()
                .when()
                .log().all()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .extract().response(); //метод, который полученный ответ в виде результата запишетв переменную response
        Assert.assertEquals(response.getStatusCode(), 200, "The actual statusCode is not 200");
        Assert.assertEquals(response.body().jsonPath().getInt("data[0].id"), 7, "The actual statusCode is not 7");
        Assert.assertEquals(response.body().jsonPath().getString("data[1].email"), "lindsay.ferguson@reqres.in");
        Assert.assertEquals(response.body().jsonPath().getString("data[2].last_name"), "Funke");
    }

    // Пример выполнения get запроса с проверкой полей с помощью Rest Assured и переменной
    @Test
    public void getListUser_4() {
        given()
                .when()
                .baseUri(BASE_URI)
                .log().all()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("data[0].id", Matchers.equalTo(7))
                .body("data[1].email", Matchers.equalTo("lindsay.ferguson@reqres.i"));
    }

    @Test
    public void createUser_1() {
        String user = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        RestAssured.given()
                //.header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .baseUri(BASE_URI)
                .log().all()
                .post("/api/users")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }


}

