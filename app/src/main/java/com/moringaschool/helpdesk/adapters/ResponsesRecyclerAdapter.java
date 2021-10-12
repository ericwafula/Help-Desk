package com.moringaschool.helpdesk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.helpdesk.R;

import java.util.ArrayList;

public class ResponsesRecyclerAdapter extends RecyclerView.Adapter<ResponsesRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> mResponses = new ArrayList<>();
    private ArrayList<String> mUpvotes =  new ArrayList<>();
    private ArrayList<String> mDownvotes =  new ArrayList<>();

    public ResponsesRecyclerAdapter(Context mContext, ArrayList<String> mResponses, ArrayList<String> mUpvotes, ArrayList<String> mDownvotes) {
        this.mContext = mContext;
        this.mResponses = mResponses;
        this.mUpvotes = mUpvotes;
        this.mDownvotes = mDownvotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.responses_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.response.setText(mResponses.get(position));
        holder.upvote.setText(mUpvotes.get(position));
        holder.downvote.setText(mDownvotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView response;
        TextView upvote;
        TextView downvote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            response = itemView.findViewById(R.id.response);
            upvote = itemView.findViewById(R.id.upvote);
            downvote = itemView.findViewById(R.id.downvote);
        }
    }
}
