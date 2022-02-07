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
import com.tuto.switch2.UI.list.ChildViewState;

public class ChildAdapter extends ListAdapter<ChildViewState, ChildAdapter.ChildViewHolder> {


    public ChildAdapter() {
        super(new ChildDiffCallBack());
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.enfant_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.enfant_name);
        }

        public void bind(ChildViewState viewState) {
            textView.setText(viewState.getName());
        }
    }

    public static class ChildDiffCallBack extends DiffUtil.ItemCallback<ChildViewState> {

        @Override
        public boolean areItemsTheSame(@NonNull ChildViewState oldItem, @NonNull ChildViewState newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChildViewState oldItem, @NonNull ChildViewState newItem) {
            return oldItem.equals(newItem);
        }
    }
}
