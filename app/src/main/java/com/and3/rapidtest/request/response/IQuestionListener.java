package com.and3.rapidtest.request.response;

import com.and3.rapidtest.base.Question;

import java.util.List;

/**
 * Created by and3 on 29/07/16.
 */
public interface IQuestionListener {
    void onQuestionsSuccess(List<Question> questionsList);
    void onQuestionsFailed();

    interface Created {
        void onQuestionCreatedSuccess(Question question);
        void onQuestionsCreatedFailed();
    }
}
