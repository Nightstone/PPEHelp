package com.software.hearth.ppehelp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.hearth.ppehelp.databinding.ChooseNameCellBinding;

import java.io.Serializable;


public class ChooseNameListAdapter extends RecyclerView.Adapter<ChooseNameListAdapter.ChooseNameListViewHolder> {

    String[] adapterArrayOfChooses;
    private LayoutInflater layoutInflater;
    private OnChooseNameListener listener;

    public void setListener(OnChooseNameListener listener) {
        this.listener = listener;
    }

    public static class ChooseNameListViewHolder extends RecyclerView.ViewHolder {

        ChooseNameCellBinding binding;
        public ChooseNameListViewHolder(ChooseNameCellBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public ChooseNameListAdapter(String[] arrayOfChooses) {
        adapterArrayOfChooses = arrayOfChooses;
    }

    @NonNull
    @Override
    public ChooseNameListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ChooseNameCellBinding cellBinding = DataBindingUtil.inflate(layoutInflater, R.layout.choose_name_cell, viewGroup, false);
        return new ChooseNameListViewHolder(cellBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseNameListViewHolder chooseNameListViewHolder, final int i) {
        chooseNameListViewHolder.binding.cellTextView.setText(adapterArrayOfChooses[i]);
        chooseNameListViewHolder.binding.cellTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onChooseNameClicked(adapterArrayOfChooses[i]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return adapterArrayOfChooses.length;
    }

    public interface OnChooseNameListener  {
        void onChooseNameClicked(String name);
    }
}
