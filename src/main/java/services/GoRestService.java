package services;

import io.restassured.response.Response;
import models.CreateUserModel;


public class GoRestService extends BaseService {

    public static Response createUser(final CreateUserModel createUserModel){
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v1/users");
    }

    public static Response updateUser(String jsonBody, int userID){
        return defaultRequestSpecification().body(jsonBody)
                .when()
                .put("/public/v1/users" + userID);
    }
    public static Response getUser( int userID){
        return defaultRequestSpecification()
                .when()
                .get("/public/v1/users/"+userID);
    }
    public static Response getUsers( ){
        return defaultRequestSpecification()
                .when()
                .get("/public/v1/users");
    }



}
