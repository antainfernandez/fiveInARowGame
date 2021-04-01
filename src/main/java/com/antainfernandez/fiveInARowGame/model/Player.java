package com.antainfernandez.fiveInARowGame.model;

public class Player {

    private String name;

    private char disk;

    public Player(){
          super();  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getDisk() {
        return disk;
    }

    public void setDisk(char disk) {
        this.disk = disk;
    }
}
