package com.ispring.gameplane.user;

import org.litepal.crud.DataSupport;

/**
 * Created by red on 2018/6/8.
 * used to save user scores that can be retrieved from DB;
 * And show for next time login
 */

public class User extends DataSupport{
    private int id;
    private String userName;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    private int profileId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int score;
    private String date;
}
