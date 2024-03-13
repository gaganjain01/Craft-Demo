package com.myproject.demo.controller;

import com.myproject.demo.entity.PlayerEntity;
import com.myproject.demo.service.PlayerInterface;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/api")
public class PlayerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlayerInterface playerInterface;

    /****
     *
     *
     * @return String
     */
    @Operation(summary = "This method is a hello endpoint")
    @ApiResponse(responseCode = "200", description = "Hello Successfull")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @GetMapping("/hello")
    public String demoFunc()
    {
        logger.info("Inside hello endpoint");
        return "Hello";
    }

    @Operation(summary = "This method is to get list of all players")
    @ApiResponse(responseCode = "200", description = "Successful")
    @GetMapping("/players")
    public ResponseEntity<List<PlayerEntity>> getAllPlayers()
    {
        logger.info("Handling Request for getAllPlayers");
        return new ResponseEntity<>(playerInterface.getAllPlayers(), HttpStatus.OK);
    }

    @Operation(summary = "This method is to get a player with given id")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "404", description = "Player not found")
    @GetMapping("/players/{playerId}")
    public ResponseEntity<PlayerEntity> getPlayer(@PathVariable String playerId)
    {
        logger.info("Handling Request for getPlayer");
        return new ResponseEntity<>(playerInterface.getPlayerById(playerId), HttpStatus.OK);
    }


    @Operation(summary = "This method is test create demo records")
    @ApiResponse(responseCode = "201", description = "Resource created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/createPlayers")
    public ResponseEntity<String> insertPlayersFromCSV(@RequestPart("file") MultipartFile file){
        logger.info("Inside insertPlayersFromCSV endpoint...");
        if(file.isEmpty())
            return new ResponseEntity<>("File is empty !", HttpStatus.BAD_REQUEST);
        String s = null;
        try {
            s = playerInterface.loadPlayers(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }

}
