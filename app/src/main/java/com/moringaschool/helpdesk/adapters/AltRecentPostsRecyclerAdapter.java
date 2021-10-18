package com.moringaschool.helpdesk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.models.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AltRecentPostsRecyclerAdapter extends RecyclerView.Adapter<AltRecentPostsRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<Result> mResults;

    public AltRecentPostsRecyclerAdapter(Context mContext, List<Result> mResults) {
        this.mContext = mContext;
        this.mResults = mResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_posts_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindResults(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        TextView cardBody;
        TextView cardReadMore;
        RelativeLayout relativeLayout;
        private Context mContext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardBody = itemView.findViewById(R.id.card_body);
            cardReadMore = itemView.findViewById(R.id.card_read_more);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            mContext = itemView.getContext();
        }

        public void bindResults(Result result){
            cardTitle.setText(result.getTitle());
            cardBody.setText(result.getBody());
            cardReadMore.setText(R.string.read_more_java);
        }
    }
}
