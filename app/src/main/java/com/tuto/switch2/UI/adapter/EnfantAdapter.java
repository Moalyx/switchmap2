package com.tuto.switch2.UI.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.tuto.switch2.R;
import com.tuto.switch2.model.Enfant;

public class EnfantAdapter extends ListAdapter<Enfant, EnfantAdapter.EnfantViewHolder> {


    public EnfantAdapter() {
        super(new EnfantDiffCallBack());
    }

    @NonNull
    @Override
    public EnfantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EnfantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.enfant_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EnfantViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public static class EnfantViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public EnfantViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.enfant_name);
        }

        public void bind(Enfant enfant) {
            textView.setText(enfant.getName());
        }
    }

    public static class EnfantDiffCallBack extends DiffUtil.ItemCallback<Enfant> {

        @Override
        public boolean areItemsTheSame(@NonNull Enfant oldItem, @NonNull Enfant newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Enfant oldItem, @NonNull Enfant newItem) {
            return oldItem.equals(newItem);
        }
    }
}
