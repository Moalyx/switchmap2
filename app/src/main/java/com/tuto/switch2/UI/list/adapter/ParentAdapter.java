package com.tuto.switch2.UI.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.tuto.switch2.R;
import com.tuto.switch2.UI.list.ParentViewState;


public class ParentAdapter extends ListAdapter<ParentViewState, ParentAdapter.ParentViewHolder> {


    private final OnUserClickedListener listener;

    public ParentAdapter(@NonNull OnUserClickedListener listener) {
        super(new ParentDiffCallBack());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        holder.bind(getItem(position), listener);
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ConstraintLayout constraintLayout;

        public void bind(@NonNull ParentViewState parent, @NonNull OnUserClickedListener listener) {
            textView.setText(parent.getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUserClicked(parent.getId());
                }
            });
        }

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.rowlayout);
            textView = itemView.findViewById(R.id.parent_name);
        }
    }

    public static class ParentDiffCallBack extends DiffUtil.ItemCallback<ParentViewState> {

        @Override
        public boolean areItemsTheSame(@NonNull ParentViewState oldItem, @NonNull ParentViewState newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ParentViewState oldItem, @NonNull ParentViewState newItem) {
            return oldItem.equals(newItem);
        }
    }

    public interface OnUserClickedListener {
        void onUserClicked(int id);
    }

}
