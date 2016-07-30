package com.and3.rapidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.and3.rapidtest.base.Choice;
import com.and3.rapidtest.base.Question;
import com.and3.rapidtest.request.response.IQuestionListener;
import com.and3.rapidtest.util.ChoiceAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity implements IQuestionListener, ChoiceAdapter.OnItemClickListener {

    private static final String TAG = QuestionActivity.class.getSimpleName();

    @Bind(R.id.recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ChoiceAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);
        Question.api(this).getQuestions(this);

        setTextToolbar("");
        setSupportActionBar(toolbar);
    }

    private void setTextToolbar(String title) {
        if (!title.isEmpty() && !title.contains("?")) title += "?";
        toolbar.setTitle(title);
    }

    @Override
    public void onQuestionsSuccess(List<Question> questionsList) {
        Log.d(TAG, "onQuestionsSuccess: ");
        setList(questionsList.get(0).getChoices());
        setTextToolbar(questionsList.get(0).getQuestion());
    }

    @Override
    public void onQuestionsFailed() {
        Log.e(TAG, "onQuestionsFailed: ");
    }

    //Recycler
    @Override
    public void onClick(View view, Choice item) {
        startActivity(new Intent(this, QuestionActivity.class));
    }

    public void setList(final List<Choice> choices) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChoiceAdapter(choices, this, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
