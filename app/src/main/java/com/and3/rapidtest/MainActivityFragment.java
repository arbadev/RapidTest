package com.and3.rapidtest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.buttonBegin)
    public void onQuestions(View view) {
        startActivity(new Intent(getContext(), QuestionActivity.class));
    }

    @OnClick(R.id.buttonCreate)
    public void onCreate(View view) {
        startActivity(new Intent(getContext(), CreateQuestion.class));
    }
}
