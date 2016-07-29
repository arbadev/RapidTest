package com.and3.rapidtest.request.question;

import android.content.Context;
import android.util.Log;

import com.and3.rapidtest.base.Question;
import com.and3.rapidtest.request.RequestManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by and3 on 28/07/16.
 */
public class QuestionHttp extends RequestManager {

    public static final String url = "/questions";
    private static final String TAG = QuestionHttp.class.getSimpleName();
    private final Context mContext;

    public QuestionHttp(Context context) {
        mContext = context;
    }

    private static IQuestion api() {
        return request().create(IQuestion.class);
    }

    public Call<List<Question>> getQuestions() {
        Call call = api().getQuestions();
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
        return call;
    }
}
