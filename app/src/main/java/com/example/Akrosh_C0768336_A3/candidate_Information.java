package com.example.Akrosh_C0768336_A3;

import java.io.Serializable;

public class candidate_Information implements Serializable {

    public candidate_Information(int id, String name, int votes) {
        this.id = id;
        this.name = name;
        this.votes = votes;
    }

    private int id;
    private String name;
    private int votes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return name;
    }
}

