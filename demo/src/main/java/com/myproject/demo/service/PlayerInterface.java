package com.myproject.demo.service;

import com.myproject.demo.entity.PlayerEntity;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlayerInterface {

    List<PlayerEntity> getAllPlayers();
    PlayerEntity getPlayerById(String Id);

    String loadPlayers(MultipartFile file) throws IOException, CsvValidationException;

}
