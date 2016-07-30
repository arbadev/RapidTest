package com.and3.rapidtest.base;

import android.content.Context;

import com.and3.rapidtest.request.choice.ChoiceHttp;
import com.and3.rapidtest.request.question.QuestionHttp;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("url")
    @Expose
    private String url;
    private boolean selected;

    /**
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public boolean isSelected() {
        return selected;
    }

    //**********************************************************************************************
    // HTTP
    //**********************************************************************************************

    public static ChoiceHttp api(Context context) {
        return new ChoiceHttp(context);
    }

}
