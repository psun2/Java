package com.lec.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class) // ※ Junit4 사용시 @WebMvcTest(TestRestController.class) @Slf4j
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void home() {


    }

    @Test
    void admin() {
    }

    @Test
    void member() {
    }

    @Test
    void logout() {
    }

    @Test
    void logoutOk() {
    }

    @Test
    void accessError() {
    }
}