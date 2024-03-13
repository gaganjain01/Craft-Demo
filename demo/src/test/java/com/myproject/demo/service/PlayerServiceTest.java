package com.myproject.demo.service;

import com.myproject.demo.entity.PlayerEntity;
import com.myproject.demo.repository.PlayerRepository;
import com.myproject.demo.service.impl.PlayerInterfaceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    PlayerEntity pe;

    @InjectMocks
    private PlayerInterfaceImpl playerInterfaceImpl;

    @Mock
    PlayerInterface playerInterfaceMock;

    @Mock
    private PlayerRepository playerRepositoryMock;

    @BeforeEach
    public void init(){
        pe = new PlayerEntity();
        pe.setPlayerId("testId");
        pe.setBirthCity("san jose");
        pe.setBirthCountry("US");
    }

    @Test
    public void testGetAllPlayers()
    {
        when(playerRepositoryMock.findAll()).thenReturn(Arrays.asList(pe));
        List<PlayerEntity> actualResult = playerInterfaceImpl.getAllPlayers();
        assertEquals(Arrays.asList(pe), actualResult);
        verify( playerRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testGetPlayerById_withOk()
    {
        when(playerRepositoryMock.findById(anyString())).thenReturn(Optional.ofNullable(pe));
        PlayerEntity actualResult = playerInterfaceImpl.getPlayerById("testId");
        assertEquals(Arrays.asList(pe), actualResult);
//        verify( playerRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testLoadPlayers() throws IOException {
        byte[] content = "playerId,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst\n1,1990,1,1,USA,CA,SF,2022,1,1,USA,CA,SF,John".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("data.csv", "data.csv", "text/csv", content);

        String result = playerInterfaceImpl.loadPlayers(multipartFile);

        assertEquals("Successful", result);
        verify(playerRepositoryMock, times(1)).save(any(PlayerEntity.class));
    }



}
