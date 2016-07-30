package com.and3.rapidtest.request.choice;

import com.and3.rapidtest.base.Choice;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by and3 on 29/07/16.
 */
public interface IChoice {

    @POST
    Call<Choice> postChoice(@Url String choiceUrl);

}
