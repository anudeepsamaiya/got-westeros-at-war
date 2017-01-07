package com.anudeepsamaiya.got.Model;

/**
 * Created by anudeepsamaiya on 7/1/17.
 */

public class KingModel {

    Long _id;
    String Name;
    Long currentRating;

    public int getBattlesWon() {
        return battlesWon;
    }

    public void setBattlesWon(int battlesWon) {
        this.battlesWon = battlesWon;
    }

    public int getBattlesLost() {
        return battlesLost;
    }

    public void setBattlesLost(int battlesLost) {
        this.battlesLost = battlesLost;
    }

    int battlesWon;
    int battlesLost;

    public KingModel() {

    }

    public KingModel(String name, Long currentRating) {
        Name = name;
        this.currentRating = currentRating;
    }

    public KingModel(Long _id, String name, Long currentRating) {
        this._id = _id;
        Name = name;
        this.currentRating = currentRating;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Long currentRating) {
        this.currentRating = currentRating;
    }
}
