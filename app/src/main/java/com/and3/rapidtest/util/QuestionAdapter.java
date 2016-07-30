package com.and3.rapidtest.util;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.and3.rapidtest.R;
import com.and3.rapidtest.base.Choice;
import com.and3.rapidtest.base.Question;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by and3 on 30/07/16.
 */
public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = QuestionAdapter.class.getSimpleName();

    private List<Question> mList;
    private OnItemClickListener mListener;
    private Context mContext;

    public static class ViewHolderBody extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_choice)
        public TextView tvChoice;

        public ViewHolderBody(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public QuestionAdapter(List<Question> list, OnItemClickListener listener, Context context) {
        mList = list;
        mListener = listener;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolderBody(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ViewHolderBody viewHolderBody = (ViewHolderBody) holder;

        final Question item = mList.get(position);


        viewHolderBody.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(view, item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void updateAdapter() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    // Interface for receiving click events from list items
    public interface OnItemClickListener {
        void onClick(View view, Question item, int position);
    }
}