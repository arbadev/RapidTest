package com.and3.rapidtest.request.question;

import com.and3.rapidtest.base.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by and3 on 28/07/16.
 */
public interface IQuestion {

    @GET(QuestionHttp.url)
    Call<List<Question>> getQuestions();

    @POST(QuestionHttp.url)
    Call<Question> postQuestion(
            @Body Question.Body body
    );
}
