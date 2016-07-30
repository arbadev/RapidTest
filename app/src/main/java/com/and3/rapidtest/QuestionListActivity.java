package com.and3.rapidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.and3.rapidtest.base.Choice;
import com.and3.rapidtest.base.Question;
import com.and3.rapidtest.request.response.IQuestionListener;
import com.and3.rapidtest.util.ChoiceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.http.Body;

public class QuestionListActivity extends AppCompatActivity implements IQuestionListener, ChoiceAdapter.OnItemClickListener {

    private static final String TAG = QuestionListActivity.class.getSimpleName();

    private ChoiceAdapter mAdapter;

    @Bind(R.id.recycler)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        ButterKnife.bind(this);

        Question.api(this).getQuestions(this);
    }

    public void setList(final List<Choice> choices) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(QuestionListActivity.this));
        mAdapter = new ChoiceAdapter(choices, QuestionListActivity.this, QuestionListActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onQuestionsSuccess(List<Question> questionsList) {
        Log.d(TAG, "onQuestionsSuccess: ");
        setList(questionsList.get(0).getChoices());
    }

    @Override
    public void onQuestionsFailed() {
        Log.e(TAG, "onQuestionsFailed: ");
    }

    //Recycler
    @Override
    public void onClick(View view, Choice item) {

    }
}
