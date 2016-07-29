package com.and3.rapidtest.base;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by and3 on 28/07/16.
 */

public class Choice {

    @SerializedName("choice")
    @Expose
    private String choice;
    @SerializedName("votes")
    @Expose
    private int votes;

    /**
     *
     * @return
     * The choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     *
     * @param choice
     * The choice
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

    /**
     *
     * @return
     * The votes
     */
    public int getVotes() {
        return votes;
    }

    /**
     *
     * @param votes
     * The votes
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
