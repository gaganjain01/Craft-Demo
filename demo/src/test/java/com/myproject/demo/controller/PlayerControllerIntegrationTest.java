package com.myproject.demo.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Any available port, avoid port conflics in CI/CD pipelines
public class PlayerControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate; //allows to make requests

//    @MockBean
//    private DemoRepository demoRepository;

    @Test
    public void contextLoads() throws JSONException //Tries to find a class annotated with SpringBootApplication and launches everything
    {
       // String response = this.restTemplate.getForObject("/api/v1/getDemo", String.class);
        //JSONAssert.assertEquals("[]", response, false);
    }
}
