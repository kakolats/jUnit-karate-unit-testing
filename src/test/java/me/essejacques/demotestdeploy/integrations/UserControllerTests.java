package me.essejacques.demotestdeploy.integrations;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void findAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect( status().isOk())
                .andExpect( jsonPath( "$.length()").value(3))
                .andExpect(
                        jsonPath( "$.[0].name").value("Stephane")
                );

    }

    @Test
    @Order(2)
    public  void createUser() throws Exception {
        mockMvc.perform(
                post("/users"  )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Kakolats\"," +
                                "\"email\":\"kakolats@mail.com\"," +
                                "\"adresse\":\"Sahm\"," +
                                "\"password\":\"password\"" +
                                "}")
        )
        .andExpect( status().isOk())
        .andExpect( jsonPath( "$.name").value("Kakolats"));
    }

    @Test
    @Order(3)
    public void getUserById() throws Exception {
        mockMvc.perform(get("/users/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").isString())
                .andExpect(jsonPath("$.adresse").isString())
                .andExpect(jsonPath("$.password").isString());
    }

    @Test
    @Order(4)
    public void updateUser() throws Exception {
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"id\":1," +
                                "\"name\":\"Updated Name\"," +
                                "\"adresse\":\"Updated@gmail.com\"," +
                                "\"password\":\"updatedpassword\"" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.adresse").value("Updated@gmail.com"))
                .andExpect(jsonPath("$.password").value("updatedpassword"));
    }

    @Test
    @Order(5)
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Deletion successful"));
    }

}
