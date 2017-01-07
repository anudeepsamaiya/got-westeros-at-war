package com.anudeepsamaiya.got;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.anudeepsamaiya.got.Model.KingModel;
import com.anudeepsamaiya.got.databinding.ItemKingsLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anudeepsamaiya on 7/1/17.
 */

public class KingsAdapter extends RecyclerView.Adapter<KingsAdapter.ViewHolder> {
    List<KingModel> kingModelList;

    public KingsAdapter(List<KingModel> kingModelList) {
        if (kingModelList == null)
            kingModelList = new ArrayList<>();
        this.kingModelList = kingModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemKingsLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_kings_layout, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(kingModelList.get(position).getName());
        holder.highestRating.setText(String.valueOf(kingModelList.get(position).getCurrentRating()));
//        holder.battleStrength.setText(kingModelList.get(position).getName());

        if (!kingModelList.get(position).getName().isEmpty()) {
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(kingModelList.get(position).getName().substring(0, 1),
                            ColorGenerator.MATERIAL.getRandomColor());

            holder.img.setImageDrawable(drawable);
        }


    }

    @Override
    public int getItemCount() {
        return kingModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView battleStrength, highestRating, name;
        ImageView img;

        public ViewHolder(final View itemView) {
            super(itemView);
            ItemKingsLayoutBinding binding = DataBindingUtil.getBinding(itemView);
            img = binding.imgKing;
            battleStrength = binding.textBattleStrength;
            highestRating = binding.textHighestRating;
            name = binding.kingName;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), KingsDetailActivity.class);
                    intent.putExtra("king", kingModelList.get(getAdapterPosition()).getName());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
