package com.problemonute.problemonute.models;

import java.io.Serializable;

/**
 * Created by Hercson on 6/1/2017.
 */

public class User implements Serializable {

    private String username;
    private String gender;
    private long score;

    public User(){

    }

    public User(String username, String gender, int score){
        this.setUsername(username);
        this.setGender(gender);
        this.setScore(score);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
