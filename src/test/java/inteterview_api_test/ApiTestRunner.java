package inteterview_api_test;

import interviewfrontendtest.utils.TestUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CreateUserModel;
import models.PayLoadUtility;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import services.GoRestService;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class ApiTestRunner {

    static String targetUserId;

    @Test
    public void Users_CreateUsers_Success() {

        final CreateUserModel creatUserModel = new CreateUserModel(TestUtility.generateName(), "female",
                TestUtility.generateEmail(), "active");

        Response response = GoRestService.createUser(creatUserModel);
        response.then()
                .statusCode(SC_CREATED)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(creatUserModel.getName()));
        System.out.println(creatUserModel.getName());
        System.out.println(response.getBody().prettyPrint());

    }

    @Test
    public void Users_GetUsers_Success() {
        Response usersResponse = GoRestService.getUsers();
        usersResponse.then().log().all().assertThat().statusCode(SC_OK);
        System.out.println(GoRestService.getUsers().getBody().prettyPrint());
    }

    @Test
    public void getUser() {
        Response response = RestAssured.given().auth().none().when().get("https://gorest.co.in/public/v2/users/2315")
                .then().extract().response();

        int respondCode = response.getStatusCode();
        Assert.assertEquals(respondCode, SC_OK);
        System.out.println(response.getBody().prettyPrint());
    }

    String payLoadBody = null;

    @Test
    public void Users_Update_eUser_Test() {

        targetUserId = GoRestService.getUsers().jsonPath().getString("data.id[1]");
        String updatedName = TestUtility.generateName();

        Response response = RestAssured.given().auth().oauth2("fa80cece96297cd1ee8f66607d62a94723a4ddd79769451e5a6ef9efba66ca61").
                header("Content-Type", "application/json").
                body(payLoadBody = PayLoadUtility.getPayLoad(updatedName, "male",
                        TestUtility.generateEmail(), "active")).when()
                .put("https://gorest.co.in/public/v2/users/" + targetUserId).then().extract().response();

        int respondCode = response.getStatusCode();
        String nameAfterUpdated = response.jsonPath().getString("name");

        Assert.assertEquals(respondCode, SC_OK);
        Assert.assertEquals(updatedName, nameAfterUpdated);

    }

}


