package com.myproject.demo.service.impl;

import com.myproject.demo.entity.PlayerEntity;
import com.myproject.demo.enums.ErrorCodes;
import com.myproject.demo.exception.InternalServerException;
import com.myproject.demo.exception.ResourceNotFoundException;
import com.myproject.demo.repository.PlayerRepository;
import com.myproject.demo.service.PlayerInterface;
import com.myproject.demo.util.FileUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerInterfaceImpl implements PlayerInterface {

    @Autowired
    private PlayerRepository playerRepository;


    @Override
    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public PlayerEntity getPlayerById(String Id) {

        Optional<PlayerEntity> optionalPlayerEntity = playerRepository.findById(Id);
        if(optionalPlayerEntity.isPresent())
            return optionalPlayerEntity.get();
        else
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.toString(), ErrorCodes.RESOURCE_NOT_FOUND.getDescription());
    }

    @Override
    public String loadPlayers(MultipartFile multipartFile) {

        try {
            File file = FileUtil.convertMultipartToFile(multipartFile);
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextLine;
            boolean flag = true;
            while ((nextLine = reader.readNext()) != null) {
                if (flag) {
                    flag = false;
                    continue;
                }

                PlayerEntity player = new PlayerEntity();
                player.setPlayerId(nextLine[0]);
                player.setBirthYear(nextLine[1]!=""?Integer.parseInt(nextLine[1]):0);
                player.setBirthMonth(nextLine[2]!=""?Integer.parseInt(nextLine[2]):0);
                player.setBirthDay(nextLine[3]!=""?Integer.parseInt(nextLine[3]):0);
                player.setBirthCountry(nextLine[4]);
                player.setBirthState(nextLine[5]);
                player.setBirthCity(nextLine[6]);
                player.setDeathYear(nextLine[7]!=""?Integer.parseInt(nextLine[7]):0);
                player.setDeathMonth(nextLine[8]!=""?Integer.parseInt(nextLine[8]):0);
                player.setDeathDay(nextLine[9]!=""?Integer.parseInt(nextLine[9]):0);
                player.setDeathCountry(nextLine[10]);
                player.setDeathState(nextLine[11]);
                player.setDeathCity(nextLine[12]);
                player.setNameFirst(nextLine[13]);
                playerRepository.save(player);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Issue while processing CSV file");
        }
        return "Successful" ;
    }
}
