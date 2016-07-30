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
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

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

    private ChoiceAdapter mAdapter;
    private List<Question> mQuestionList;
    private Question mCurrent;
    private int mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        ButterKnife.bind(this);
        Question.api(this).getQuestions(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mQuestionList != null && !mQuestionList.isEmpty()) {
            if (mPosition == mQuestionList.size()) {
                mPosition--;
            }
            mCurrent = mQuestionList.get(mPosition);
            setList(mCurrent.getChoices());
            setTextToolbar(mCurrent.getQuestion());
        }
    }

    private synchronized void setTextToolbar(String title) {
        if (!title.isEmpty() && !title.contains("?")) title += "?";
        setTitle(title);
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
        Toast.makeText(this, getString(R.string.error_try_later), Toast.LENGTH_SHORT).show();
        finish();
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
        Toast.makeText(this, getString(R.string.error_try_later), Toast.LENGTH_SHORT).show();
    }
}
