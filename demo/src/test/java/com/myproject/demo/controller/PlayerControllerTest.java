package com.myproject.demo.controller;

import com.myproject.demo.entity.PlayerEntity;
import com.myproject.demo.service.PlayerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class) //Only want to launch the controller: Spring mock mvc helps us..to say that
public class PlayerControllerTest {

    PlayerEntity pe;
    @Autowired //It is present as a bean so we can autowire it
    MockMvc mockMvc ; //To enable to call uri and test controllers

    @MockBean
    private PlayerInterface playerInterface;


    @BeforeEach
    public void init(){
        pe = new PlayerEntity();
        pe.setPlayerId("testId");
        pe.setBirthCity("san jose");
        pe.setBirthCountry("US");
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        when(playerInterface.getAllPlayers()).thenReturn(Arrays.asList(pe));
        RequestBuilder request = get("/api/players")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetPlayerById_withOk() throws Exception {
        when(playerInterface.getPlayerById(anyString())).thenReturn(pe);
        RequestBuilder request = get("/api/players/testId")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetPlayerById() throws Exception {
        PlayerEntity player = new PlayerEntity();
        player.setPlayerId("1");

        when(playerInterface.getPlayerById("1")).thenReturn(player);

        mockMvc.perform(get("/api/players/{playerId}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playerId").value("1"));
    }

    @Test
    public void testInsertPlayersFromCSV() throws Exception {
        byte[] content = "playerId,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst\n1,1990,1,1,USA,CA,SF,2022,1,1,USA,CA,SF,John".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("file", "data.csv", "text/csv", content);

        when(playerInterface.loadPlayers(any(MultipartFile.class))).thenReturn("Successful");

        mockMvc.perform(multipart("/api/createPlayers").file("file", multipartFile.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andExpect(content().string("Successful"));
    }




}
