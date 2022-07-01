/*
 * Copyright (c) 2023 Sergey Alekseev (https://github.com/rev1an).
 *
 * Software distributed under MIT license.
 */

package reqres.in.tests;

import com.github.rev1an.core.json.JacksonMapper;
import com.github.rev1an.core.junit.annotation.HttpConfig;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

@HttpConfig(basePath = "https://reqres.in/")
public class UsersApiTests {

    private final JacksonMapper mapper;

    private String newId = null;

    public UsersApiTests(JacksonMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    @Order(1)
    public void test_postUser(RequestSpecification request) {
        var body = mapper.asJsonNode("""
                                      {
                                          "name": "yolos",
                                          "job": "leader"
                                      }
                                     """);
        var response = request.body(body.toString())
                              .post("/api/users")
                              .then()
                              .statusCode(201)
                              .body("id", Matchers.not(Matchers.emptyOrNullString()))
                              .body("createdAt", Matchers.not(Matchers.emptyOrNullString()))
                              .extract();
        this.newId = response.jsonPath().get("$.id");
    }

    @Test
    @Order(2)
    @DisabledIf(value = "postNotCreated",
                disabledReason = "User must be created!")
    public void test_deleteUser(RequestSpecification request) {
        request.delete("/api/users/" + this.newId)
               .then()
               .statusCode(204);
    }

    @Test
    public void test_listUsers(RequestSpecification request) {
        request.get("/api/users?page=1")
               .then()
               .statusCode(200)
               .body("page", Matchers.is(1))
               .body("data.collect { it.avatar }", Matchers.everyItem(Matchers.matchesRegex("^https://reqres.in/img/faces/.*$"))) // GPath
               .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/reqres/in/test_listUsers.json"));
    }

    private boolean postNotCreated() {
        return "0".equals(this.newId);
    }

}
