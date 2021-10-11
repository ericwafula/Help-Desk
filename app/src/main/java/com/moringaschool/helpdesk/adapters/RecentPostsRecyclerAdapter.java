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
import java.util.zip.Inflater;

public class RecentPostsRecyclerAdapter extends RecyclerView.Adapter<RecentPostsRecyclerAdapter.ViewHolder> {
    Context mContext;
    ArrayList<String> cardTitles = new ArrayList<>();
    ArrayList<String> cardBodies = new ArrayList<>();
    ArrayList<String> cardReadMores = new ArrayList<>();

    public RecentPostsRecyclerAdapter(Context mContext, ArrayList<String> cardTitles, ArrayList<String> cardBodies, ArrayList<String> cardReadMores) {
        this.mContext = mContext;
        this.cardTitles = cardTitles;
        this.cardBodies = cardBodies;
        this.cardReadMores = cardReadMores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_posts_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardTitle.setText(cardTitles.get(position));
        holder.cardBody.setText(cardBodies.get(position));
        holder.cardReadMore.setText(cardReadMores.get(position));
    }

    @Override
    public int getItemCount() {
        return cardTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        TextView cardBody;
        TextView cardReadMore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardBody = itemView.findViewById(R.id.card_body);
            cardReadMore = itemView.findViewById(R.id.card_read_more);
        }
    }
}
