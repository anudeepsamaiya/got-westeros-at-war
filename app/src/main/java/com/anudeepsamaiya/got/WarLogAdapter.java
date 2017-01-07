package com.anudeepsamaiya.got;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.anudeepsamaiya.got.Model.WarLogModel;
import com.anudeepsamaiya.got.databinding.ItemWarlogListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anudeepsamaiya on 7/1/17.
 */

public class WarLogAdapter extends RecyclerView.Adapter<WarLogAdapter.ViewHolder> {
    List<WarLogModel> dataset;

    WarLogAdapter(List<WarLogModel> dataset) {
        if (dataset == null)
            dataset = new ArrayList<>();

        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWarlogListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_warlog_list, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textBattleName.setText(dataset.get(position).getName());
        holder.textBattleYear.setText(String.valueOf(dataset.get(position).getYear()));
        holder.textBattleLoc.setText(dataset.get(position).getLocation());
        holder.textBattleOutcome.setText(dataset.get(position).getAttackerKing() + " " +
                dataset.get(position).getAttackerOutcome());

        holder.textMajorDeath.setText(String.valueOf(dataset.get(position).getMajorDeath()));
        holder.textMajorCapture.setText(String.valueOf(dataset.get(position).getMajorCapture()));

        holder.textAttackerKing.setText(dataset.get(position).getAttackerKing());
        holder.textDefenderKing.setText(dataset.get(position).getDefenderKing());

        TextDrawable attackerDrawable = TextDrawable.builder()
                .buildRound(dataset.get(position).getAttackerKing().substring(0, 1),
                        ColorGenerator.MATERIAL.getRandomColor());

        TextDrawable defenderDrawable = TextDrawable.builder()
                .buildRound(dataset.get(position).getAttackerKing().substring(0, 1),
                        ColorGenerator.MATERIAL.getRandomColor());

        holder.attackerKing.setImageDrawable(attackerDrawable);
        holder.defenderKing.setImageDrawable(defenderDrawable);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void updateDataset(List<WarLogModel> dataset) {
        this.dataset = dataset;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView attackerKing;
        ImageView defenderKing;

        TextView textAttackerKing;
        TextView textDefenderKing;
        TextView textBattleName, textBattleOutcome, textBattleLoc, textBattleYear;
        TextView textMajorDeath;
        TextView textMajorCapture;

        ViewHolder(View item) {
            super(item);
            ItemWarlogListBinding binding = DataBindingUtil.getBinding(item);

            textBattleName = binding.textBattleName;
            attackerKing = binding.imgAttackerKing;
            defenderKing = binding.imgDefenderKing;
            textAttackerKing = binding.textAttackerKing;
            textDefenderKing = binding.textDefenderKing;
            textBattleLoc = binding.textBattleLoc;
            textBattleOutcome = binding.textBattleOutcome;
            textBattleYear = binding.textBattleYear;

            textMajorCapture = binding.textMajorCapture;
            textMajorDeath = binding.textMajorDeath;
        }
    }
}
