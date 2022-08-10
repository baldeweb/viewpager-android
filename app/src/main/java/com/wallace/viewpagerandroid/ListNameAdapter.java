package com.wallace.viewpagerandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wallace.viewpagerandroid.databinding.ItemPageBinding;

import java.util.ArrayList;

public class ListNameAdapter extends RecyclerView.Adapter<ListNameAdapter.ListNameViewHolder> {
    private ArrayList<String> listName;
    private ItemPageBinding binding;

    public ListNameAdapter(ArrayList<String> listName) {
        this.listName = listName;
    }

    @NonNull
    @Override
    public ListNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemPageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ListNameViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ListNameViewHolder holder, int position) {
        binding.tvwName.setText(listName.get(position));
    }

    @Override
    public int getItemCount() {
        return listName.size();
    }

    static class ListNameViewHolder extends RecyclerView.ViewHolder {
        public ListNameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
