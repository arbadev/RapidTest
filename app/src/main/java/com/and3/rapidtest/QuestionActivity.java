package com.and3.rapidtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.and3.rapidtest.base.Choice;
import com.and3.rapidtest.base.Question;
import com.and3.rapidtest.request.response.IChoiceListener;
import com.and3.rapidtest.request.response.IQuestionListener;
import com.and3.rapidtest.util.ChoiceAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity implements IQuestionListener, ChoiceAdapter.OnItemClickListener, IChoiceListener {

    private static final String TAG = QuestionActivity.class.getSimpleName();
    private static final String CURRENT = "current";
    private static final String LIST = "list";

    @Bind(R.id.recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ChoiceAdapter mAdapter;
    private List<Question> mQuestionList;
    private Question mCurrent;
    private int mPosition;


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
    public void onBackPressed() {
        mPosition--;
        if (mPosition >= 0) {
            mCurrent = mQuestionList.get(mPosition);
            setList(mCurrent.getChoices());
            setTextToolbar(mCurrent.getQuestion());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onQuestionsSuccess(List<Question> questionsList) {
        Log.d(TAG, "onQuestionsSuccess: ");
        mQuestionList = questionsList;
        if (mQuestionList != null && !mQuestionList.isEmpty()) {
            mCurrent = questionsList.get(0);
            mPosition = 0;
            setList(mCurrent.getChoices());
            setTextToolbar(mCurrent.getQuestion());
        }
    }

    @Override
    public void onQuestionsFailed() {
        Log.e(TAG, "onQuestionsFailed: ");
    }

    //Recycler
    @Override
    public void onClick(View view, Choice item, int position) {
        Choice.api(this).postChoice(item.getUrl(), this);
        mCurrent.getChoices().set(position, item);
        mQuestionList.set(mPosition, mCurrent);
    }

    public void setList(final List<Choice> choices) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChoiceAdapter(choices, this, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChoiceSuccess() {
        mPosition++;
        if (mPosition < mQuestionList.size()) {
            mCurrent = mQuestionList.get(mPosition);
            setTitle(mCurrent.getQuestion());
            setList(mCurrent.getChoices());
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onChoiceFailed() {

    }
}
