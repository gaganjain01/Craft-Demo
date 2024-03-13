package com.myproject.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Player_Table")
public class PlayerEntity {

    @Id
    private String playerId;
    //@Column(name="title_name", nullable = false)
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private int deathYear;
    private int deathMonth;
    private int deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
}
