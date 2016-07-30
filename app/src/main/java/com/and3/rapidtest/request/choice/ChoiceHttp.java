package com.and3.rapidtest.request.choice;

import android.content.Context;
import android.util.Log;

import com.and3.rapidtest.base.Choice;
import com.and3.rapidtest.request.RequestManager;
import com.and3.rapidtest.request.question.QuestionHttp;
import com.and3.rapidtest.request.response.IChoiceListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by and3 on 29/07/16.
 */
public class ChoiceHttp extends RequestManager {

    private static final String TAG = QuestionHttp.class.getSimpleName();
    private final Context mContext;

    public ChoiceHttp(Context context) {
        mContext = context;
    }

    private static IChoice api() {
        return request().create(IChoice.class);
    }

    public Call<Choice> postChoice(String url, final IChoiceListener listener) {
        Call<Choice> call = api().postChoice(url);
        call.enqueue(new Callback<Choice>() {
            @Override
            public void onResponse(Call<Choice> call, Response<Choice> response) {
                if (response.isSuccessful()) {
                    listener.onChoiceSuccess();
                } else {
                    listener.onChoiceFailed();
                }
            }

            @Override
            public void onFailure(Call<Choice> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                listener.onChoiceFailed();
            }
        });
        return call;
    }
}
