package com.and3.rapidtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.and3.rapidtest.base.Question;
import com.and3.rapidtest.request.response.IQuestionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateQuestion extends AppCompatActivity implements IQuestionListener.Created {

    @Bind(R.id.et_question)
    EditText etQuestion;
    @Bind(R.id.et_choice)
    EditText etChoice;
    @Bind(R.id.layout)
    LinearLayout layout;

    List<EditText> choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        ButterKnife.bind(this);

        choices = new ArrayList<>();

        choices.add(etChoice);
    }

    @OnClick(R.id.button_add)
    public void add(View view) {
        EditText editText = new EditText(this);
        choices.add(editText);
        layout.addView(editText);
    }

    @OnClick(R.id.button_save)
    public void save(View view) {
        List<String> choicesList = new ArrayList<>();

        for (EditText e :
                choices) {
            if (e.getText() != null &&
                    !e.getText().toString().isEmpty()) {
                choicesList.add(e.getText().toString());
            }
        }

        if (choicesList.isEmpty() || getQuestion().isEmpty()) {
            return;
        }

        Question.Body body = new Question.Body();

        body.setQuestion(getQuestion());
        body.setChoices(choicesList);

        Question.api(this).postQuestions(body, this);
    }

    private String getQuestion() {
        if (etQuestion.getText() != null) {
            return etQuestion.getText().toString();
        }
        return "";
    }

    @Override
    public void onQuestionCreatedSuccess(Question question) {
        Toast.makeText(this, getString(R.string.saved), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onQuestionsCreatedFailed() {
        Toast.makeText(this, getString(R.string.error_try_later), Toast.LENGTH_SHORT).show();
    }
}

