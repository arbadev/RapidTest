package com.and3.rapidtest.base;

import android.content.Context;

import com.and3.rapidtest.base.Choice;
import com.and3.rapidtest.request.question.QuestionHttp;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by and3 on 28/07/16.
 */

public class Question {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
    @SerializedName("choices")
    @Expose
    private List<Choice> choices = new ArrayList<Choice>();

    /**
     * @return The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return The publishedAt
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * @param publishedAt The published_at
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     * @return The choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices The choices
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public class Body {

        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("choices")
        @Expose
        private List<String> choices = new ArrayList<String>();

        /**
         * @return The question
         */
        public String getQuestion() {
            return question;
        }

        /**
         * @param question The question
         */
        public void setQuestion(String question) {
            this.question = question;
        }

        /**
         * @return The choices
         */
        public List<String> getChoices() {
            return choices;
        }

        /**
         * @param choices The choices
         */
        public void setChoices(List<String> choices) {
            this.choices = choices;
        }

        @Override
        public String toString() {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            return json;
        }
    }


    //**********************************************************************************************
    // HTTP
    //**********************************************************************************************

    public static QuestionHttp api(Context context) {
        return new QuestionHttp(context);
    }
}
