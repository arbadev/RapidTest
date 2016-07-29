package com.and3.rapidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.and3.rapidtest.base.Question;

public class QuestionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Question.api(this).getQuestions();
    }
}
