package com.and3.rapidtest.base;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

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

public class Question implements Parcelable {

    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("published_at")
    @Expose
    private String publishedAt;
    @SerializedName("choices")
    @Expose
    private List<Choice> choices = new ArrayList<Choice>();

    protected Question(Parcel in) {
        question = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(publishedAt);
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
