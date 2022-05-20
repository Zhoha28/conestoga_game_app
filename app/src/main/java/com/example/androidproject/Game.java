package com.example.androidproject;

public class Game {

    private String gameId;
    private String gameName;
    private String gameGenre;
    private String gamePrice;
    private String gamedescription;

    //default constructor
    public Game() {
    }

    //custom
    public Game(String gameId, String gameName, String gameGenre, String gamePrice, String gamedescription) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameGenre = gameGenre;
        this.gamePrice = gamePrice;
        this.gamedescription = gamedescription;

    }

    //getters
    public String getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public String getGamePrice() {
        return gamePrice;
    }

    public String getGamedescription() {
        return gamedescription;
    }
}
