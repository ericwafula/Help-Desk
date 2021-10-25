package com.moringaschool.helpdesk.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.models.Result;
import com.moringaschool.helpdesk.ui.fragments.PostedQuestionFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.Inflater;

public class AltRecentPostsRecyclerAdapter extends RecyclerView.Adapter<AltRecentPostsRecyclerAdapter.ViewHolder> implements Filterable {
    private Context mContext;
    private List<Result> mResults;
    private List<Result> mResultsAll;

    public AltRecentPostsRecyclerAdapter(Context mContext, List<Result> mResults) {
        this.mContext = mContext;
        this.mResults = mResults;
        this.mResultsAll = new ArrayList<>(mResults);
    }

    public void setmResults(List<Result> mResults) {
        this.mResults = mResults;
        notifyDataSetChanged();
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
        Bundle bundle = new Bundle();
        bundle.putString("pTitle", holder.cardTitle.getText().toString());
        bundle.putString("pBody", holder.cardBody.getText().toString());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                PostedQuestionFragment postedQuestionFragment = new PostedQuestionFragment();
                postedQuestionFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, postedQuestionFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mResults != null){
            return mResults.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        // runs on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Result> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                filteredList.addAll(mResultsAll);
            } else {
                for (Result result : mResultsAll){
                    if (result.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()) || result.getBody().toLowerCase().contains(charSequence.toString().toLowerCase()) || result.getCategory().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(result);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        //runs on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mResults.clear();
            mResults.addAll((Collection<? extends Result>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        TextView cardBody;
        TextView cardCategory;
        RelativeLayout relativeLayout;
        private Context mContext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardBody = itemView.findViewById(R.id.card_body);
            cardCategory = itemView.findViewById(R.id.card_read_more);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            mContext = itemView.getContext();
        }

        public void bindResults(Result result){
            cardTitle.setText(result.getTitle());
            cardBody.setText(result.getBody());
            cardCategory.setText(result.getCategory());
        }
    }
}
