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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by and3 on 30/07/16.
 */
public class ChoiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = ChoiceAdapter.class.getSimpleName();

    private List<Choice> mList;
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

    public ChoiceAdapter(List<Choice> list, OnItemClickListener listener, Context context) {
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

        final Choice item = mList.get(position);

        viewHolderBody.tvChoice.setText(item.getChoice());

        Log.d(TAG, "onBindViewHolder: item = " + item.toString());

        if (item.isSelected()) {
            viewHolderBody.itemView.setBackgroundResource(R.drawable.button_border_selected);
        } else {
            viewHolderBody.itemView.setBackgroundResource(R.drawable.button_border);
        }

        if (isSelectedOne()) {
            viewHolderBody.itemView.setEnabled(item.isSelected());
        }

        viewHolderBody.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setSelected(true);
                mListener.onClick(view, item, position);
            }
        });
    }

    private boolean isSelectedOne() {
        for (Choice choice :
                mList) {
            if (choice.isSelected()) return true;
        }
        return false;
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
        void onClick(View view, Choice item, int position);
    }
}