package com.example.volleydenemetwo;

import java.io.Serializable;

/**
 * Created by kasimadalan on 1.05.2018.
 */

public class Kelimeler implements Serializable {

    private String turkce;
    private String likebtn;
    private int id;

    public Kelimeler() {
    }

    public Kelimeler(String turkce, int id, String likebtn) {
        this.turkce = turkce;
        this.likebtn = likebtn;
        this.id = id;
    }

    public String getTurkce() {
        return turkce;
    }

    public void setTurkce(String turkce) {
        this.turkce = turkce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLikebtn() {
        return likebtn;
    }

    public void setLikebtn(String likebtn) {
        this.likebtn = likebtn;
    }
}
