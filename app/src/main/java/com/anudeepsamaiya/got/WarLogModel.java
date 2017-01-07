package com.anudeepsamaiya.got;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anudeepsamaiya on 7/1/17.
 */

public class WarLogModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("battle_number")
    @Expose
    private int battleNumber;
    @SerializedName("attacker_king")
    @Expose
    private String attackerKing;
    @SerializedName("defender_king")
    @Expose
    private String defenderKing;
    @SerializedName("attacker_1")
    @Expose
    private String attacker1;
    @SerializedName("attacker_2")
    @Expose
    private String attacker2;
    @SerializedName("attacker_3")
    @Expose
    private String attacker3;
    @SerializedName("attacker_4")
    @Expose
    private String attacker4;
    @SerializedName("defender_1")
    @Expose
    private String defender1;
    @SerializedName("defender_2")
    @Expose
    private String defender2;
    @SerializedName("defender_3")
    @Expose
    private String defender3;
    @SerializedName("defender_4")
    @Expose
    private String defender4;
    @SerializedName("attacker_outcome")
    @Expose
    private String attackerOutcome;
    @SerializedName("battle_type")
    @Expose
    private String battleType;
    @SerializedName("major_death")
    @Expose
    private int majorDeath;
    @SerializedName("major_capture")
    @Expose
    private int majorCapture;
    @SerializedName("attacker_size")
    @Expose
    private String attackerSize;
    @SerializedName("defender_size")
    @Expose
    private String defenderSize;
    @SerializedName("attacker_commander")
    @Expose
    private String attackerCommander;
    @SerializedName("defender_commander")
    @Expose
    private String defenderCommander;
    @SerializedName("summer")
    @Expose
    private String summer;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("note")
    @Expose
    private String note;

    /**
     * No args constructor for use in serialization
     */
    public WarLogModel() {
    }

    /**
     * @param majorCapture
     * @param region
     * @param location
     * @param battleType
     * @param defenderSize
     * @param attackerSize
     * @param name
     * @param year
     * @param battleNumber
     * @param note
     * @param defender3
     * @param defenderCommander
     * @param defenderKing
     * @param defender4
     * @param majorDeath
     * @param attackerOutcome
     * @param attackerKing
     * @param attacker2
     * @param attacker1
     * @param attacker4
     * @param attacker3
     * @param summer
     * @param defender2
     * @param defender1
     * @param attackerCommander
     */
    public WarLogModel(String name, int year, int battleNumber, String attackerKing,
                       String defenderKing, String attacker1, String attacker2, String attacker3,
                       String attacker4, String defender1, String defender2, String defender3,
                       String defender4, String attackerOutcome, String battleType, int majorDeath,
                       int majorCapture, String attackerSize, String defenderSize,
                       String attackerCommander, String defenderCommander, String summer,
                       String location, String region, String note) {
        this.name = name;
        this.year = year;
        this.battleNumber = battleNumber;
        this.attackerKing = attackerKing;
        this.defenderKing = defenderKing;
        this.attacker1 = attacker1;
        this.attacker2 = attacker2;
        this.attacker3 = attacker3;
        this.attacker4 = attacker4;
        this.defender1 = defender1;
        this.defender2 = defender2;
        this.defender3 = defender3;
        this.defender4 = defender4;
        this.attackerOutcome = attackerOutcome;
        this.battleType = battleType;
        this.majorDeath = majorDeath;
        this.majorCapture = majorCapture;
        this.attackerSize = attackerSize;
        this.defenderSize = defenderSize;
        this.attackerCommander = attackerCommander;
        this.defenderCommander = defenderCommander;
        this.summer = summer;
        this.location = location;
        this.region = region;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBattleNumber() {
        return battleNumber;
    }

    public void setBattleNumber(int battleNumber) {
        this.battleNumber = battleNumber;
    }

    public String getAttackerKing() {
        return attackerKing;
    }

    public void setAttackerKing(String attackerKing) {
        this.attackerKing = attackerKing;
    }

    public String getDefenderKing() {
        return defenderKing;
    }

    public void setDefenderKing(String defenderKing) {
        this.defenderKing = defenderKing;
    }

    public String getAttacker1() {
        return attacker1;
    }

    public void setAttacker1(String attacker1) {
        this.attacker1 = attacker1;
    }

    public String getAttacker2() {
        return attacker2;
    }

    public void setAttacker2(String attacker2) {
        this.attacker2 = attacker2;
    }

    public String getAttacker3() {
        return attacker3;
    }

    public void setAttacker3(String attacker3) {
        this.attacker3 = attacker3;
    }

    public String getAttacker4() {
        return attacker4;
    }

    public void setAttacker4(String attacker4) {
        this.attacker4 = attacker4;
    }

    public String getDefender1() {
        return defender1;
    }

    public void setDefender1(String defender1) {
        this.defender1 = defender1;
    }

    public String getDefender2() {
        return defender2;
    }

    public void setDefender2(String defender2) {
        this.defender2 = defender2;
    }

    public String getDefender3() {
        return defender3;
    }

    public void setDefender3(String defender3) {
        this.defender3 = defender3;
    }

    public String getDefender4() {
        return defender4;
    }

    public void setDefender4(String defender4) {
        this.defender4 = defender4;
    }

    public String getAttackerOutcome() {
        return attackerOutcome;
    }

    public void setAttackerOutcome(String attackerOutcome) {
        this.attackerOutcome = attackerOutcome;
    }

    public String getBattleType() {
        return battleType;
    }

    public void setBattleType(String battleType) {
        this.battleType = battleType;
    }

    public int getMajorDeath() {
        return majorDeath;
    }

    public void setMajorDeath(int majorDeath) {
        this.majorDeath = majorDeath;
    }

    public int getMajorCapture() {
        return majorCapture;
    }

    public void setMajorCapture(int majorCapture) {
        this.majorCapture = majorCapture;
    }

    public String getAttackerSize() {
        return attackerSize;
    }

    public void setAttackerSize(String attackerSize) {
        this.attackerSize = attackerSize;
    }

    public String getDefenderSize() {
        return defenderSize;
    }

    public void setDefenderSize(String defenderSize) {
        this.defenderSize = defenderSize;
    }

    public String getAttackerCommander() {
        return attackerCommander;
    }

    public void setAttackerCommander(String attackerCommander) {
        this.attackerCommander = attackerCommander;
    }

    public String getDefenderCommander() {
        return defenderCommander;
    }

    public void setDefenderCommander(String defenderCommander) {
        this.defenderCommander = defenderCommander;
    }

    public String getSummer() {
        return summer;
    }

    public void setSummer(String summer) {
        this.summer = summer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

