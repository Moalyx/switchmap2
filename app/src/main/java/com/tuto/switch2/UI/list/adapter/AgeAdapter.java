package com.tuto.switch2.UI.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.tuto.switch2.R;
import com.tuto.switch2.UI.list.AgeViewState;

public class AgeAdapter extends ListAdapter<AgeViewState, AgeAdapter.AgeViewHolder> {


    public AgeAdapter() {
        super(new AgeDiffCallBack());
    }

    @NonNull
    @Override
    public AgeAdapter.AgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AgeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.age_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AgeAdapter.AgeViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public static class AgeViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public AgeViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.age);
        }

        public void bind(AgeViewState viewState) {
            textView.setText(viewState.getAge());
        }
    }

    public static class AgeDiffCallBack extends DiffUtil.ItemCallback<AgeViewState> {

        @Override
        public boolean areItemsTheSame(@NonNull AgeViewState oldItem, @NonNull AgeViewState newItem) {
            return oldItem.getAgeId() == newItem.getAgeId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull AgeViewState oldItem, @NonNull AgeViewState newItem) {
            return oldItem.equals(newItem);
        }
    }
}
